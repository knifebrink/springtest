package com.spring.test.chapter6.interesting.circledi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ASonP {
    @Autowired
    public Father father;

    @Async
    public void doBadThing(){
        System.out.println("null");
    }
}
