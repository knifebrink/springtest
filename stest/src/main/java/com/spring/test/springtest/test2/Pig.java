package com.spring.test.springtest.test2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Scope(scopeName = "session")
//@SessionScope
public class Pig {
    private static int dogId = 0;
    public int id = -1;
    public Pig(){
        dogId++;
        id = dogId;
        System.out.println("生成一只猪："+id);
    }
}
