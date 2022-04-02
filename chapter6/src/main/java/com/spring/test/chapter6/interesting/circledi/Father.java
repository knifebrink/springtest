package com.spring.test.chapter6.interesting.circledi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Father {
    public String name = "fa1";
    @Autowired
    public Mother mother;
    @Autowired
    public ASonP sonP;
//    @Async
    public void doBadThing(){
        System.out.println("null");
    }
}
