package com.spring.test.chapter6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author brink
 * 2021/7/6 11:38
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    // 数据库事务
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,timeout = 1)
    public int insertUser(User user) {
        System.out.println("insert");
//        userMapper.selectUser();
        return userMapper.insertUser(user);
//        int a=  1/0;
    }

    public void selectUser(){

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void reduceCount(){
//        System.out.println("开始");
        User user = userMapper.selectUserById("1");
        user.setCount(user.getCount()+1);
        userMapper.updateUser(user);
//        System.out.println("结束");
    }
}
