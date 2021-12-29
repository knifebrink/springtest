package com.spring.test.springtest.springtest;

import lombok.Data;


public class Dad {
    private Son son;

    public Dad(){
        System.out.println("初始化父亲");
    }

    public void setSon(Son son) {
        System.out.println("父亲设置儿子");
        this.son = son;
    }

    @Override
    public String toString() {
        return "这是父亲: ";
    }
}
