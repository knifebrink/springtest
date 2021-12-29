package com.spring.test.springtest.springtest;

/**
 * @author brink
 * 2021/12/1 10:04
 */
public class School {
    private SpringUser springUser;
    private SpringUser setterSpringUser;

//    public School(){
//        System.out.println("初始化school:---------");
//    }
    public School(SpringUser springUser){
        this.springUser = springUser;
        System.out.println("初始化school:---------"+springUser);
    }

    public void setSetterSpringUser(SpringUser springUser){
        setterSpringUser = springUser;
        System.out.println("构造器注入:---------"+springUser);
    }
}
