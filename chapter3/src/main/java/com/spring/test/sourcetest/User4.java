package com.spring.test.sourcetest;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/7/5 15:39
 */
@Data
@Component
public class User4 {
    private Long id;
    private String name="user4 只做生命周期测试";
    public User4(){
        System.out.println("这是user4的初始化，主要看日志位置");
    }
}
