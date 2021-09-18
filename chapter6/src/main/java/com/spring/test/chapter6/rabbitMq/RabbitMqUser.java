package com.spring.test.chapter6.rabbitMq;

import lombok.Data;

import java.io.Serializable;

/**
 * @author brink
 * 2021/9/18 10:25
 */
@Data
public class RabbitMqUser implements Serializable { // 需要接受序列化
    private String id;
    private String name;
    public RabbitMqUser(String id,String name){
        this.id = id;
        this.name = name;
    }
    public RabbitMqUser() {
    }
}
