package com.spring.test.springtest.test;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/8/27 12:03
 * 测试@Primary
 */
@Data
@Component
public class UserSub1 implements UserP{
    private String id = "1";
}
