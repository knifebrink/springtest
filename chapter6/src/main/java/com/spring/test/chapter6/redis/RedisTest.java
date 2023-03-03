package com.spring.test.chapter6.redis;


import com.alibaba.fastjson.JSON;
import com.spring.test.chapter6.common.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.*;

/**
 * redis常用结构测试
 * https://blog.csdn.net/xpsallwell/article/details/84030285
 */
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisTest {
    //    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate2; // 只能是这种泛型结构，即使后面的是object 也很容易出错
    @Autowired
    private RedisTemplate<String, Object> redisTemplate3;

    private static final String KEY_STR_PREFIX = "string:";
    private static final String KEY_LIST_PREFIX = "list:";

    @Test
    public void test1() {
        // string结构操作
        String strKey = KEY_STR_PREFIX + "kkkka";
        redisTemplate2.opsForValue().set(strKey, "这是一个redis字符串");
        redisTemplate3.opsForValue().set(KEY_STR_PREFIX + "obj", Person.builder().name("人").id(111L).build());
        Object o = redisTemplate2.opsForValue().get(strKey);
//        log.warn("获取的对象: {}", o);

        // list结构操作
        List<String> list = new ArrayList<>();
//        List<String> list = new ArrayList<>(); // 这样会造成入参错误，因为kv值不一致，我吐了
        list.add("a");
        list.add("b");
        list.add("c");
        String listKey = "list:" + "num";
        redisTemplate2.delete(listKey);
        // 往右推入全部obj
        redisTemplate2.opsForList().rightPushAll(listKey, list); // 因为入参的问题
//        redisTemplate.opsForList().rightPushAll(listKey, list.toArray());
        // 获取列表中的所有元素，-1代表最右侧
        List<String> o2 = redisTemplate2.opsForList().range(listKey, 0, -1);
        log.warn("获取的对象2{}", o2);
        log.warn("获取的对象3{}", redisTemplate2.opsForList().index(listKey, 1));


        // 哈希
        String hashKey = "hash:";
        String hashKey1 = hashKey + "1";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "张三");
        hashMap.put("2", "李四");
        redisTemplate2.delete(hashKey1);
        redisTemplate2.opsForHash().putAll(hashKey1, hashMap);
        // 获取全部
        Map<Object, Object> outMap = redisTemplate2.opsForHash().entries(hashKey1);
        log.warn("获取全部hash对象：{}", JSON.toJSONString(outMap));
        // 获取单个
        log.warn("获取的hash对象：{}", redisTemplate2.opsForHash().get(hashKey1, "1"));
        // 加入单个
        redisTemplate2.opsForHash().put(hashKey1, "3", "王五");

        // 集合 set
        String setKey = "set:";
        String setKey1 = setKey + "1";
        redisTemplate2.delete(setKey1);
        redisTemplate2.opsForSet().add(setKey1, "a");
        redisTemplate2.opsForSet().add(setKey1, "a");
        redisTemplate2.opsForSet().add(setKey1, "b");
        redisTemplate2.opsForSet().add(setKey1, "c");
        Set<String> set = redisTemplate2.opsForSet().members(setKey1);
        log.warn("set：{}", set);


        // 有序集合zset
        String zSetKey = "zSet:";
        String zSetKey1 = zSetKey + "1";
        redisTemplate2.delete(zSetKey1);

        redisTemplate2.opsForZSet().add(zSetKey1, "a", 80);
        redisTemplate2.opsForZSet().add(zSetKey1, "a", 99);
        redisTemplate2.opsForZSet().add(zSetKey1, "b", 98);
        redisTemplate2.opsForZSet().add(zSetKey1, "c", 97);
        redisTemplate2.opsForZSet().add(zSetKey1, "d", 100);
        Object zSetO = redisTemplate2.opsForZSet().rangeWithScores(zSetKey1, 0, -1);
        Object zSetO2 = redisTemplate2.opsForZSet().range(zSetKey1, 0, -1);
        log.warn("zSetO：{}", JSON.toJSONString(zSetO));
        log.warn("zSetO2：{}", JSON.toJSONString(zSetO2));
    }


    // 测试一个连接多次执行的性能
    // 结论：execute不会几何级增加，批量执行有效，奇怪没错。
    /**
     * 结论：
     * 普通的批量执行没什么区别
     *RedisTemplate每执行一个方法，就意味着从Redis连接池中获取一条连接，使用SessionCallBack接口后，就意味着所有的操作都来自同一条Redis连接，避免了命令在不同连接上执行。
     * 因为事务或者流水线执行命令都先缓存到一个队列里，所以在执行方法后并不会马上返回结果，结果是通过最后的一次性执行返回的，这点在使用的时候要注意。
     * 在需要保证数据一致性的情况下，要使用事务。
     * 在需要执行多个命令时，可以使用流水线，它让命令缓存到一个队列，然后一次性发给Redis服务器执行，从而提高性能
     *
     * 引用
     */
    @Test
    public void test2() {
        String zSetKey = "zSet:";
        String zSetKey1 = zSetKey + "1";
        redisTemplate2.opsForZSet().score(zSetKey1, "a"); // 预先连接，排除这些时间
        long loopCount = 1000;

        // 直接执行
        long time = System.currentTimeMillis();
        List<Object> alist = new ArrayList<>();
        for (int i = 0; i < loopCount; i++) {
            alist.add(redisTemplate2.opsForZSet().score(zSetKey1, "a"));
        }
        log.warn("执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(alist));
        log.warn("--------------");
        alist.clear();


        time = System.currentTimeMillis();
        // 执行多个命令
        Object result2 = redisTemplate2.execute(new SessionCallback() { // 很奇怪，并不能提高速度
            @Override
            public String execute(RedisOperations rt) throws DataAccessException {
                for (int i = 0; i < loopCount; i++) {
                    alist.add(rt.opsForZSet().score( zSetKey1, "a"));
                    if (i == 0){
                        log.warn("输出 i = 0时：{}", JSON.toJSONString(alist));
                    }
                }
                return null;
            }
        });
        log.warn("普通批量执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(alist));
        log.warn("输出：{}", JSON.toJSONString(result2));
        log.warn("--------------");
        alist.clear();
        time = System.currentTimeMillis();

        // 流水线

        List<Object> cList = new ArrayList<>();
        Object o = redisTemplate2.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                ArrayList<Object> cList = new ArrayList<>();
                for (int i = 0; i < loopCount; i++) {
                    cList.add(operations.opsForZSet().score((K) zSetKey1, "a"));
                    if (i == 0){
                        log.warn("输出 i = 0时：{}", JSON.toJSONString(alist));
                    }
                }

                operations.opsForZSet().score((K) zSetKey1, "b");
                return null;
            }
        });
        log.warn("流水线批量执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(alist));
        log.warn("输出：{}", JSON.toJSONString(o));
        log.warn("--------------");
        time = System.currentTimeMillis();

        // 事务
        Object result4 = redisTemplate2.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.watch((K) zSetKey1);
                operations.multi();
                // 命令不会马上执行
                List<Object> dList = new ArrayList<>();
                for (int i = 0; i < loopCount; i++) {

                    dList.add(operations.opsForZSet().score((K) zSetKey1, "a"));
                    if (i == 0){
                        log.warn("输出 i = 0时：{}", JSON.toJSONString(alist));
                    }
                }

                Object result = operations.exec();
                return result;
            }
        });
        log.warn("事务批量执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(result4));

        time = System.currentTimeMillis();
    }
}
