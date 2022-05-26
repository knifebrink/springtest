package com.spring.test.chapter6.chapter6;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * https://blog.csdn.net/mengsofts/article/details/88074790
 * 《JavaEE互联网轻量级框架整合开发》
 * 《mysql45讲》
 */
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

    public void logResult(){
        log.warn("end result {}",userMapper.selectUserById(id).getCount());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void doSomeThing()  {
        MDC.put("traceId","事务a ");
        System.out.println("事务a开始");
        log.info("事务类型：{}",userMapper.selectTc());
        doSomeA();
        System.out.println("事务a结束");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void doSomeThingByThread2()  {
        MDC.put("traceId","事务b ");
        System.out.println("事务b开始");
        doSomeB();
        System.out.println("事务b结束");
    }
    int operation = 4; // 操作 2:第二类更新丢失 3: 脏读 4: 不可重复读
    public void doSomeA(){
        if(operation == 1) {
            funA();
        }else if(operation == 2){
            IILossA();
        }else if(operation == 3){
            dirtyReadingA();
        }else if(operation == 4){
            NonRepeatableReadingA();
        }
    }

    public void doSomeB(){
        if(operation == 1) {
            funB();
        }else if(operation == 2){
            IILossB();
        }else if(operation == 3){
            dirtyReadingB();
        }else if(operation == 4){
            NonRepeatableReadingB();
        }
    }
    // 随意测试
    public void funA(){
        User user = readUser();
        sleep(3000);
        user = readUser();
        reduceAndUpdate(user);
    }

    // 随意测试b
    public void funB(){
        sleep(500);
        User user = readUser();
        reduceAndUpdate(user);
        user = readUser();

    }

    // 第二类丢失更新
    public void IILossA(){
        User user = readUser();// t1
        sleep(300);
        reduceAndUpdate(user);// t2
        sleep(3000);
        // t5 end
    }

    // 第二类丢失更新 b
    public void IILossB(){
        User user = readUser();// t1
        sleep(500);
        reduceAndUpdate(user);// t3
        // t4 end
    }

    // 脏读A 需要将隔离级别设置为未提交读 READ_UNCOMMITTED
    // 将隔离级别设置为提交读后，虽然解决脏读问题，但更容易第二类丢失，因为读不到B的数据
    public void dirtyReadingA(){
        User user = readUser();// t1
        sleep(1000);
        user = readUser();// t4.1 重新读取
        reduceAndUpdate(user);// t4.2
        // t5
    }

    public void dirtyReadingB(){
        User user = readUser();// t2
        reduceAndUpdate(user); // t3
        sleep(3000);
        throw new RuntimeException("没出错，但我要丢");// t6
    }

    // 不可重复读现象 因为读取不到事务B提交的数据
    /**
     *  可提交读：事务a可以在b提交事务后读取
     *  不可重复读：提交后也不能重复读取
     */
    public void NonRepeatableReadingA(){
        User user = readUser();// t1
        sleep(1000);
        user = readUser();// t4 读取
        // 发现有钱了，可以消费了

        sleep(3000);
        user = readUser();//t7 再读取一遍，发现没钱了，不够钱用
        //
    }

    public void NonRepeatableReadingB(){
        sleep(500);
        User user = readUser();// t2
        reduceAndUpdate(user); // t3
        sleep(2000);
        user = readUser();// t5
        reduceAndUpdate(user); // t6

    }
    // 幻读

    private void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
