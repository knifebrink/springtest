package com.spring.test.chapter6.springbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncTest {

    @Async
    public void doSomeThingAsync(){
        log.info("当前线程是：{}",Thread.currentThread().toString());
    }

    public void doSomeThing(){
        log.info("当前线程是：{}",Thread.currentThread().toString());
    }
}
