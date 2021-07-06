package com.spring.test.chapter6;

import lombok.Data;

/**
 * @author brink
 * 2021/7/6 11:29
 */
@Data
public class User {
    private String id;
    private int count;
    private String userName;
    public User(){}
    public User(String userName,int count){
        this.userName = userName;
        this.count = count;
    }
}
