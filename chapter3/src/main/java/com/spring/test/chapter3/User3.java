package com.spring.test.chapter3;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/7/5 16:05
 */
@Data
@Component
public class User3 {
    private Long id;
    private String name;
//    @Autowired //这里会警告不建议
    private Sub3 dog;

//    public User3(@Autowired Sub3 dog){ //如果有构造方法，默认就会带@Autowired
//        this.dog = dog;
//    }
//
    public void service(){
        dog.doSomething();
    }

    public Sub3 getDog() {
        return dog;
    }

    @Autowired // 通过方法注入，有意思
    public void setDog(Sub3 dog) {
        this.dog = dog;
    }
}
