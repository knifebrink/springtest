package com.spring.test.springtest.springtest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class Dad {
    private Son son;

    public Dad(){
        System.out.println("初始化父亲");
    }

    @Autowired
    public void setSon(Son son) {
        System.out.println("父亲设置儿子");
        this.son = son;
    }

//    @Async
//    public void doBadThing(){
//        System.out.println("null");
//    }

    @Override
    public String toString() {
        return "这是父亲: ";
    }
}
