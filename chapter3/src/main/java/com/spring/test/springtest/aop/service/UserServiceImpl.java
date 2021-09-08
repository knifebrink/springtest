package com.spring.test.springtest.aop.service;

import com.spring.test.springtest.User;
import org.springframework.stereotype.Service;

/**
 * @author brink
 * 2021/9/8 15:18
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void printUser(User user) {
        System.out.println("printUser："+user.toString());
    }
}
