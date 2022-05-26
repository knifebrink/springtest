package com.spring.test.chapter6.chapter6;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
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

    /**
     * 事务隔离测试
     * 先把mybatis的缓存级别都设置一下
     */
    @Test
    public void test() throws InterruptedException {
        transactionalService.resetUser();
        Runnable runnable = () -> {

            transactionalService.doSomeThing();

        };
        Runnable runnable2 = () -> {
            transactionalService.doSomeThingByThread2();
        };
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread.start();
        thread2.start();
        Thread.sleep(5000);
        System.out.println("end");
        transactionalService.logResult();
    }

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Test
    public void test2(){
        log.warn("sql: {},{}",sqlSessionFactory,sqlSessionTemplate);
    }
}
