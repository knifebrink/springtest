package com.spring.test.chapter6.hutool;

import com.spring.test.chapter6.rabbitMq.RabbitMqApplication;
import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplicationNoDataSources
public class HutoolApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(HutoolApplication.class,args);
    }
}
