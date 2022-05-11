package com.spring.test.chapter6.swagger;

import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import org.springframework.boot.SpringApplication;

/**
 * swagger 文档测试
 * knife4j 是swagger的一款皮肤吧，可以说，基本上也是用swagger的注解
 * 主要是以@Api 开头的注解
 * 自动搜索controller了
 */
@SpringBootApplicationNoDataSources
public class Knife4jTestApplication {
    public static void main(String[] args){
        SpringApplication.run(Knife4jTestApplication.class);
    }
}
