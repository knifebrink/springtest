package com.spring.test.chapter6.springbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
@Slf4j
/**
 * 该接口用来指明：当一个bean包含在SpringApplication内，该bean就应当执行。可以在相同的应用上下文定义多个这样的bean。
 * 多个bean的先后执行顺序使用@Order注解确定。
 */
public class CommandLineRunnerTest implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunnerTest运行在初始化后");
    }
}
