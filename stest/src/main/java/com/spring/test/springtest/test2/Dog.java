package com.spring.test.springtest.test2;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON) // 单例
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //
@Scope(scopeName = "request")
public class Dog {
    private static int dogId = 0;
    public int id = -1;
    public Dog(){
        dogId++;
        id = dogId;
        System.out.println("生成一条狗："+id);
    }
}
