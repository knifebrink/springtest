package com.spring.test.chapter6.utils;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootApplication(exclude = {
//      排除数据源
        DataSourceAutoConfiguration.class,
//        禁止mongodb自动装配
        MongoAutoConfiguration.class,
//        禁止redis自动装配
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class,
//        禁止rabbitmq自动装配
        RabbitAutoConfiguration.class}
)
public @interface SpringBootApplicationNoDataSources {
}
