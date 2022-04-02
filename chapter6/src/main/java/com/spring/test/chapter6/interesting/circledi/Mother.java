package com.spring.test.chapter6.interesting.circledi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Mother {
    public String name = "mo";
    @Autowired
    public Father father;

    public Mother(){
        System.out.println(name);
    }
}
