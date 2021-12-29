package com.spring.test.springtest.springtest;

public class Son {
    private Dad dad;

    public Son(){
        System.out.println("初始化儿子");
    }

    public void setDad(Dad dad) {
        System.out.println("儿子设置父亲");
        this.dad = dad;
    }

    @Override
    public String toString() {
        return "这是儿子:";
    }


}
