package com.spring.test.chapter6.chapter6;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TransactionalService {

    @Autowired
    UserMapper userMapper;

    private String id = "999";
    public void resetUser(){
        User user = new User();
        user.setCount(10);
        user.setUserName("事务测试用");
        user.setId(id);
        userMapper.updateUser(user);

    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void doSomeThing() throws InterruptedException {
        MDC.put("traceId","事务a ");
        System.out.println("事务a开始");
        log.info("事务类型：{}",userMapper.selectTc());
        User user = readUser();
        Thread.sleep(3000);
        user = readUser();
        reduceAndUpdate(user);

        System.out.println("事务a结束");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void doSomeThingByThread2() throws InterruptedException {
        MDC.put("traceId","事务b ");
        Thread.sleep(500);
        System.out.println("事务b开始");
        User user = readUser();
        reduceAndUpdate(user);
        user = readUser();
        System.out.println("事务b结束");
    }

    private User readUser(){
        User user = userMapper.selectUserById(id);
        log.info(MDC.get("traceId")+"读取user: {} ",user.getCount());
        return user;
    }

    private void reduceAndUpdate(User user){
        user.setCount(user.getCount()-1);
        userMapper.updateUser(user);
        log.info(MDC.get("traceId")+"更新减掉user");
    }
}
