package com.spring.test.chapter6.chapter6;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author brink
 * 2022-5-25 17:02:14
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = Chapter6Application.class)
public class TransactionalTest {


    @Autowired
    TransactionalService transactionalService;
    @Test
    public void test() throws InterruptedException {
        transactionalService.resetUser();
        Runnable runnable = () -> {
            try {

                transactionalService.doSomeThing();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try {

                transactionalService.doSomeThingByThread2();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread.start();
        thread2.start();
        Thread.sleep(5000);
        System.out.println("end");
    }
}
