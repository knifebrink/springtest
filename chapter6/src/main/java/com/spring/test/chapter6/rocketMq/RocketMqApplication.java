package com.spring.test.chapter6.rocketMq;

import com.spring.test.chapter6.rabbitMq.RabbitMqApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication (exclude = {
//      排除数据源
        DataSourceAutoConfiguration.class,
//        禁止mongodb自动装配
        MongoAutoConfiguration.class,
//        禁止redis自动装配
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class,
//        禁止rabbitmq自动装配
        RabbitAutoConfiguration.class})
public class RocketMqApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(RocketMqApplication.class, args);
    }
    @Autowired
    private ProducerService producerService;

    @PostConstruct
    public void fun(){
        boolean result = producerService.send("demo1", "TAG-A", "Hello RocketMQ");
        log.info("发送：{}",result);
    }
}
