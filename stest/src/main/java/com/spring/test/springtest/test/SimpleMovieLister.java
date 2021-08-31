package com.spring.test.springtest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/8/30 15:33
 */

@Component
public class SimpleMovieLister {
    public  User user;

    @Autowired
    public void setMovieFinder(User user) {
        this.user = user;
    }
}
