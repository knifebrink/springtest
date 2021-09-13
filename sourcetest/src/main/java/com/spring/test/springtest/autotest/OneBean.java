package com.spring.test.springtest.autotest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/9/10 19:18
 */
@Component
public class OneBean implements NumBean{
    public OneBean(){
        System.out.println("初始化成功:oneBean");
    }
}
