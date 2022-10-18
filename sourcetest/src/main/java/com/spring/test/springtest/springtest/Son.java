package com.spring.test.springtest.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Son {
    private Dad dad;

    public Son(){
        System.out.println("初始化儿子");
    }

    @Autowired
    public void setDad(Dad dad) {
        System.out.println("儿子设置父亲");
        this.dad = dad;
    }

//    @Async
    public void doBadThing(){
        System.out.println("null");
    }

    @Override
    public String toString() {
        return "这是儿子:";
    }


}
