package com.spring.test.springtest.springtest;

import lombok.Data;

/**
 * @author brink
 * 2021/12/1 9:03
 */
@Data
public class SpringUser {
    private String id;
    private int count;
    private String userName;
    public SpringUser(){
        System.out.println("初始化user:---------");

    }
    public SpringUser(String userName, int count){
        this.userName = userName;
        this.count = count;
    }
}
