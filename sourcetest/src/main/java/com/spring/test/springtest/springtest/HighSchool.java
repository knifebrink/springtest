package com.spring.test.springtest.springtest;

/**
 * @author brink
 * 2021/12/1 11:22
 */
public class HighSchool {
    private SpringUser user;
    // 自动注入测试 byName
    public void setUser(SpringUser springUser){
        System.out.println("HighSchool：设置属性："+springUser);
        this.user = springUser;
    }
}
