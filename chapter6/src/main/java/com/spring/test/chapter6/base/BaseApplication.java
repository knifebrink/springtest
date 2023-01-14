package com.spring.test.chapter6.base;


import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import org.springframework.boot.SpringApplication;

@SpringBootApplicationNoDataSources
public class BaseApplication {

    public static void main(String[] args){

        SpringApplication.run(BaseApplication.class, args);
    }
}
