package com.spring.test.chapter6.springbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncTest2 {


    public void doSomeThingAsync(){
        log.info("doSomeThingAsync当前线程是：{}",Thread.currentThread().toString());
    }

    public void doSomeThing(){
        log.info("doSomeThing当前线程是：{}",Thread.currentThread().toString());
    }

    // 自身嵌套调用
    public void doInMyself(){
        log.info("自调用测试");
        doSomeThingAsync();
        log.info("自调用测试解决");
    }

}
