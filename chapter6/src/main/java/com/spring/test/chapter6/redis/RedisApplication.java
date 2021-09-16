package com.spring.test.chapter6.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * redis测试
 *
 * 主要是一个键值储存的nosql数据库，出了名快。
 * 可作为mysql的缓存。例如将大量的查询数据库的数据导入进redis再进行查询。
 *
 * 参考：深入浅出springboot 第十章
 */
@SpringBootApplication
public class RedisApplication {
	public static void main(String[] args) {
		startSpringboot(args);
//		startOne();
	}

	private static void startOne(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
		RedisTemplate<Object,Object> redisTemplate = ctx.getBean(RedisTemplate.class);
		// 支持七种数据结构，spring都分别封装对应的操作接口
		// 字符串操作接口
		redisTemplate.opsForValue().set("key1", "value1");
		// 散列操作接口
		redisTemplate.opsForHash().put("hash1", "field1", "hvalue1");
		// 列表操作接口
//		redisTemplate.opsForList()
		useSessionCallback(redisTemplate);
//		useRedisCallback(redisTemplate);
		ctx.close();
	}



	private static void startSpringboot(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class,args);
		RedisTemplate<Object,Object> redisTemplate = context.getBean("redisTemplate",RedisTemplate.class);// 多了一个stringRedisTemplate
//		System.out.println("2:"+redisTemplate);
		redisTemplate.opsForValue().set("key1222", "vualesdf");
		// 支持七种数据结构，spring都分别封装对应的操作接口
		// 字符串操作接口
		redisTemplate.opsForValue().set("key1", "value1");
		// 散列操作接口
		redisTemplate.opsForHash().put("hash1", "field1", "hvalue1");
		// 列表操作接口

		String value = (String) redisTemplate.opsForValue().get("key1222");
		System.out.println(value);
//		context.stop();
	}

	@Autowired
	private RedisTemplate redisTemplate;

	@Value("${spring.redis.password}")
	private String password;
	@PostConstruct
	public void init(){
		initRedisTemplate();
		System.out.println(redisTemplate+" "+password);
	}

	private void initRedisTemplate(){
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

}	