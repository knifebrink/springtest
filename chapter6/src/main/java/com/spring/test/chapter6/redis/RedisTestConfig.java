package com.spring.test.chapter6.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 在springboot中，可以直接在.yml中配置，然后通过IOC获取
 */
@Configuration
public class RedisTestConfig {


	/**
	 * 最主要是配置序列化
	 * 首先默认的序列化就是一种类似乱码的，需要继承Serializable
	 * 其次如果用RedisSerializer<String> 带泛型，那很多都是那样了
	 */
	@Bean(name="redisTemplate2")
	public RedisTemplate<String, Object> initRedisTemplate2(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setValueSerializer(stringRedisSerializer);// 设置值的序列器
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		redisTemplate.setHashValueSerializer(stringRedisSerializer);
		System.out.println("RedisTestConfig生成redisTemplate");
		return redisTemplate;
	}



	@Bean(name="redisTemplate3")
	public RedisTemplate<String, Object> initRedisTemplate3(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(Object.class));
//		redisTemplate.setValueSerializer(stringRedisSerializer);// 设置值的序列器
//		redisTemplate.setHashKeySerializer(stringRedisSerializer);
//		redisTemplate.setHashValueSerializer(stringRedisSerializer);
		System.out.println("RedisTestConfig生成redisTemplate");
		return redisTemplate;
	}
}