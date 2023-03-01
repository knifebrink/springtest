package com.spring.test.chapter6.redis;

import com.spring.test.chapter6.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * redis测试
 * <p>
 * 主要是一个键值储存的nosql数据库，出了名快。
 * 可作为mysql的缓存。例如将大量的查询数据库的数据导入进redis再进行查询。
 * <p>
 * 参考：深入浅出springboot 第十章
 */
@Slf4j
@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        RabbitAutoConfiguration.class,
        RedissonAutoConfiguration.class
})
public class RedisApplication {
    public static void main(String[] args) throws Exception {
        startSpringboot(args);
//		startOne();
//		testSyn(args); // 分布式锁测试


    }


    private static void startOne() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate<Object, Object> redisTemplate = ctx.getBean(RedisTemplate.class);
        // 支持七种数据结构，spring都分别封装对应的操作接口
        // 字符串操作接口
        redisTemplate.opsForValue().set("key1", "value1");
        // 散列操作接口
        redisTemplate.opsForHash().put("hash1", "field1", "hvalue1");
        // 列表操作接口
//		redisTemplate.opsForList()
        useSessionCallback(redisTemplate);
//		useRedisCallback(redisTemplate);
        System.out.println(ctx.getBean(JedisPool.class));
        ;
        ctx.close();
    }


    private static void startSpringboot(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        RedisTemplate<Object, Object> redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);// 多了一个stringRedisTemplate
        System.out.println("2:" + redisTemplate);
        redisTemplate.opsForValue().set("key1222", "vualesdf");
        // 支持七种数据结构，spring都分别封装对应的操作接口
        // 字符串操作接口
        redisTemplate.opsForValue().set("key1", "value1");
        // 散列操作接口
        redisTemplate.opsForHash().put("hash1", "field1", "hvalue1");
        // 列表操作接口

        String value = (String) redisTemplate.opsForValue().get("key1222");
        System.out.println(value);
        redisTemplate.getConnectionFactory().getConnection().close();
//		context.stop();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.redis.password}")
    private String password;

    @PostConstruct
    public void init() {
        initRedisTemplate();
        System.out.println(redisTemplate + " " + password);
    }

    private void initRedisTemplate() {
//		redisTemplate.setConnectionFactory(initConnectionFactory());
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }
//	// 需要处理底层的转换规则，如果不考虑改写底层，尽量不使用它
//	public static void useRedisCallback(RedisTemplate redisTemplate) {
//	    redisTemplate.execute(new RedisCallback() {
//	        @Override
//	        public Object doInRedis(RedisConnection rc) 
//	                throws DataAccessException {
//	            rc.set("key1".getBytes(), "value1".getBytes());
//	            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
//	            return null;
//	        }
//	    });
//	}
//
//	// 高级接口，比较友好，一般情况下，优先使用它
//	public static void useSessionCallback(RedisTemplate redisTemplate) {
//	    redisTemplate.execute(new SessionCallback() {
//	        @Override
//	        public Object execute(RedisOperations ro) 
//	                throws DataAccessException {
//	            ro.opsForValue().set("key1", "value1");
//	            ro.opsForHash().put("hash", "field", "hvalue");
//	            return null;
//	        }
//	    });
//	}

    public static void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection rc) -> {
            rc.set("key1".getBytes(), "value1".getBytes());
            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());

            return null;
        });
    }

    public static void useSessionCallback(RedisTemplate redisTemplate) {
//	    redisTemplate.execute((RedisOperations ro) -> {
//	        ro.opsForValue().set("key1", "value1");
//	        ro.opsForHash().put("hash", "field", "hvalue");
//	        return null;
//	    });
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key2", "value2");
                redisOperations.opsForHash().put("hash1", "field2", "hvalue2");

                return null;
            }
        });

    }


    /**
     * redis 分布式锁 测试
     * 关键点是，redis可以当成是线程安全的（因为单线程）
     * 1. redis 线程安全。2. redis执行的命令需要保证原子性
     * setex  设置值并设置有效时间，原子性操作
     * setnx 如果不存在设置值，并返回1；存在则不设置，并返回0
     * <p>
     * 测试结论，synchronized是真的快，100个线程只要十几秒
     * 而redis自旋，得要两秒钟，怎么这么长，可能是redisTemplate每次操作都需要重新连接的问题，可以直接用底层去搞
     * 但明白原理，先不管了。
     * 可以用redis框架
     * https://blog.csdn.net/qq_35190492/article/details/105499233
     *
     * @see <a href="https://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public static void testSyn(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        RedisTemplate<Object, Object> redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);// 多了一个stringRedisTemplate
        // 设置值如果不存在，返回成功则进入同步，否则自旋，直到设置成功
        // 需同步代码
        // 运行代码段结束，清空值
        long time2 = System.currentTimeMillis();
        redisTemplate.opsForValue().setIfAbsent("kkkkk", "11");
        System.out.println("一个操作时间：" + (System.currentTimeMillis() - time2));
        ReentrantLock reentrantLock = new ReentrantLock();
        for (int i = 0; i < 100; i++) {
            IncreaseThread increaseThread = new IncreaseThread() {
                @Override
                public void run() {
                    redisLock(redisTemplate);

//					reentrantLock.lock();
                    needSync();
//					reentrantLock.unlock();
                    redisUnLock(redisTemplate);
                }
            };
            increaseThread.start();
        }
        long time = System.currentTimeMillis();
        // 判断是否所有线程执行完毕
        while (System.currentTimeMillis() - time < 10000) {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            int count = 0;
            for (Thread thread : map.keySet()) {
                if (thread.getName().equals("IncreaseThread")) {
                    count++;
                    break;
                }
            }
            if (count == 0)
                break;
            Thread.sleep(30);

        }
        System.out.println("所计算的时间：" + (System.currentTimeMillis() - time));

        // 等上面线程完成
//		Thread.sleep(5000);

        // 同步正确应该是100 * 1000
        System.out.println("-----i:" + IncreaseThread.sum);
        context.stop();

    }

    // 只用了setnx指令 ，怎么改进呢，这样的问题就在于，由于各种原因，没有释放锁的键值(del)，那就永远进不去了
    // 如果在用setex指令，就不能保证原子性了吧
    // redisson
    private static void redisLock(RedisTemplate<Object, Object> redisTemplate) {
        while (true) {
            // setnx
            Boolean b = redisTemplate.opsForValue().setIfAbsent("kkk", "1");
//			Boolean b = redisTemplate.opsForValue().setIfAbsent("kkk", "1",10, TimeUnit.SECONDS);
            if (b != null && b)// 不存在值则上锁
                break;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void redisUnLock(RedisTemplate<Object, Object> redisTemplate) {
        redisTemplate.delete("kkk");
    }


    //	@Autowired
    private RedissonClient redisson;

    /**
     * redisson 分布式锁测试
     * https://blog.csdn.net/leijie0322/article/details/123348889
     */
//	@PostConstruct
    private void testRedissonLock() throws InterruptedException {
        log.warn("{}", redisson);
        String lockKey = "LOCK_KEY:1";
        RLock rLock = redisson.getLock(lockKey);
        rLock.lock(60000, TimeUnit.MILLISECONDS);
        log.warn("上锁");
//		boolean lockResult = rLock.tryLock(6000,TimeUnit.MILLISECONDS);
//		log.warn("获取锁{}",lockResult);
        new Thread(() -> {
//			boolean result = false;
//			try {
//				RLock rLock1 = redisson.getLock(lockKey);
//				result = rLock1.tryLock(5000, TimeUnit.MILLISECONDS);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			log.warn("线程获取锁{}",result);
//			rLock.unlock();
        }).start();
        new Thread(() -> {
            boolean result = true;
            try {
                RLock rLock1 = redisson.getLock(lockKey);
                rLock1.lock(60000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.warn("线程获取锁{}", result);
            rLock.unlock();
        }).start();
        Thread.sleep(3000);
//		rLock.unlock();
        rLock.unlock();
        log.warn("解锁");


    }


    /**
     * redisson锁计算测试
     */
//	@PostConstruct
    public void testRedissonLock2() throws InterruptedException {
        String lockKey = "LOCK_KEY:2";
        RLock rLock = redisson.getLock(lockKey);
        log.warn("redisson锁计算测试");
        IncreaseThread.sum = 0;
        for (int i = 0; i < 100; i++) {
            IncreaseThread increaseThread = new IncreaseThread() {
                @Override
                public void run() {
                    rLock.lock();


                    needSync();
                    rLock.unlock();

                }
            };
            increaseThread.start();
        }
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 100000) {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            int count = 0;
            for (Thread thread : map.keySet()) {
                if (thread.getName().equals("IncreaseThread")) {
                    count++;
                    break;
                }
            }
            if (count == 0)
                break;
            Thread.sleep(30);

        }
        log.warn("redisson锁计算 " + IncreaseThread.sum);
    }

}	