package com.spring.test.springtest.controller;

import lombok.Data;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/8/27 12:03
 */
//@Data
@Data
@Component("uss2")
@Async
public class User2 {
    public User2(){
        System.out.println("user2 init");
    }
    private String id = "1";

}
