package com.spring.test.chapter6.chapter6;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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

    public User selectUser(){
        return userMapper.selectUserById(1+"");
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void reduceCount(){
//        System.out.println("开始");
        User user = userMapper.selectUserById("1");
        user.setCount(user.getCount()-1);
        userMapper.updateUser(user);
//        System.out.println("结束");
    }

    /**
     * 此方法是用于破解，逻辑程序并不是都在事务中执行的问题，例如以上例子
     * 这部分的处理和理解，需要更深刻一些才有可能，而且这些都不如直接外面加锁比较方便，因为涉及到逻辑运算，一般还是
     * 程序语言去进行的处理
     * 好像有点怪，不好处理，原有的有线程池
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void reduceCount2() throws InterruptedException {
//        System.out.println("开始");
        User user = userMapper.selectUserById("1");
        userMapper.reduceUser(user);
        Thread.sleep(5);
        userMapper.reduceUser(user);
        Thread.sleep(5);
        userMapper.reduceUser(user);
        Thread.sleep(5);
        userMapper.reduceUser(user);
        Thread.sleep(5);
        userMapper.reduceUser(user);
        Thread.sleep(5);
//        System.out.println("结束");
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    private void reduceCount3(){
    }

}
