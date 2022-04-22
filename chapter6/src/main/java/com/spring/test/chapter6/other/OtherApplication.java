package com.spring.test.chapter6.other;

import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

@SpringBootApplicationNoDataSources
public class OtherApplication {
    public static void main(String[] args){
        SpringApplication.run(OtherApplication.class);
    }

}
