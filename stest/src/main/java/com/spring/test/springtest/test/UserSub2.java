package com.spring.test.springtest.test;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/8/27 12:03
 * 用于测试@Primary
 */
@Data
@Component
@Primary // 优先级，不带会出错
public class UserSub2 implements UserP{
    private String id = "2";
}
