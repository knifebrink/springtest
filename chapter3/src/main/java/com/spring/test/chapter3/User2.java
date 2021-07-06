package com.spring.test.chapter3;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/7/5 15:39
 */
@Data
@Component
public class User2 {
    @Value("2")
    private Long id;
    @Value("这是@component测试注入的属性")
    private String name;
}
