package com.spring.test.springtest.test;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/7/6 11:29
 */
@Data
@Component("user")
public class User {
    private String id;
    private int count;
    private String userName;
    public User(){
        System.out.println("初始化user:---------");

    }
    public User(String userName, int count){
        this.userName = userName;
        this.count = count;
    }
}
