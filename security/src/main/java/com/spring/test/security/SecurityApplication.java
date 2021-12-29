package com.spring.test.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 安全测试
 * 只要pom.xml中加了spring security依赖，就会自动开启防护
 * 原理主要是用Servlet Filter
 * 会在日志中生成密码，用户名是user
 */
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
