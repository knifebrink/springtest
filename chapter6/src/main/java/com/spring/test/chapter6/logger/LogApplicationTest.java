package com.spring.test.chapter6.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author brink
 * 2021/11/17 9:35
 * SLF4J——Simple Logging Facade For Java，使用了 外观模式。它是一个针对于各类Java日志框架的统一Facade抽象。
 * Java日志框架众多——常用的有java.util.logging,log4j, logback，commons-logging,
 * Spring框架使用的是Jakarta Commons Logging API (JCL)。
 * 而SLF4J定义了统一的日志抽象接口，而真正的日志实现则是在运行时决定的——它提供了各类日志框架的binding。
 * Logback是log4j框架的作者开发的新一代日志框架，它效率更高、能够适应诸多的运行环境，同时天然支持SLF4J。默认情况下，Spring Boot会用Logback来记录日志
 * ————————————————
 * 原文链接：https://blog.csdn.net/qq_37375501/article/details/110673347
 *
 * 可用.yml进行设置，也可用元素的log-*.xml进行设置
 * 测试需要将logback-spring2.xml 改成logback-spring.xml
 */
@SpringBootApplication
public class LogApplicationTest {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LogApplicationTest.class,args);
        Logger logger = LoggerFactory.getLogger(LogApplicationTest.class);
        logger.debug("调试");// 默认 debug并不会输出
        logger.info("这是一个log4j测试 "+logger.getClass());
        logger.warn("警告");
        logger.error("错误");

        applicationContext.stop();

    }
}
