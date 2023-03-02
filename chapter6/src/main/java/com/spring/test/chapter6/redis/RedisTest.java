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
    // 结论：execute不会几何级增加，批量执行有效
    @Test
    public void test2() {
        String zSetKey = "zSet:";
        String zSetKey1 = zSetKey + "1";
        redisTemplate2.opsForZSet().score(zSetKey1, "a"); // 预先连接，排除这些时间

        long time = System.currentTimeMillis();
        ArrayList<Object> alist = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            alist.add(redisTemplate2.opsForZSet().score(zSetKey1, "a"));
        }
        log.warn("执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(alist));

        log.warn("--------------");
        alist.clear();
        time = System.currentTimeMillis();

        redisTemplate2.execute(new SessionCallback() {
            @Override
            public String execute(RedisOperations rt) throws DataAccessException {
                for (int i = 0; i < 100; i++) {
                    alist.add(rt.opsForZSet().score( zSetKey1, "a"));
                }
                return null;
            }
        });
        log.warn("批量执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(alist));
        log.warn("--------------");

        alist.clear();

        time = System.currentTimeMillis();
        redisTemplate2.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                for (int i = 0; i < 100; i++) {
                    alist.add(operations.opsForZSet().score((K) zSetKey1, "a"));
                }
                return null;
            }
        });
        log.warn("批量执行的时间是：{}", (System.currentTimeMillis() - time));
        log.warn("输出：{}", JSON.toJSONString(alist));


    }
}
