package com.spring.test.springtest.autotest.model;

import com.spring.test.springtest.autotest.NumBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/9/13 15:12
 */
@Component
public class TwoBean  {
    public TwoBean(){
        System.out.println("bean初始化：twoBean");
    }
}
