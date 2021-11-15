package com.spring.test.springtest.mybatistest;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author brink
 * 2021/9/16 9:49
 */
@SpringBootApplication
public class MyBatisTestApplication {


    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MyBatisTestApplication.class,args);
        MyBatisTestApplication myBatisTestApplication = applicationContext.getBean(MyBatisTestApplication.class);
        TalkUserMapper talkUserMapper = applicationContext.getBean(TalkUserMapper.class);
        System.out.println(talkUserMapper);
        talkUserMapper.selectAll();
        talkUserMapper.selectByName("brink");

        talkUserMapper.toString();


        TalkUserMapper talkUserMapper2 = applicationContext.getBean(TalkUserMapper.class);

        System.out.println(talkUserMapper2);

        TalkUserBean talkUserBean = applicationContext.getBean(TalkUserBean.class);
        talkUserBean.toString();
        myBatisTestApplication.test();
        applicationContext.stop();

    }

    @Autowired
    SqlSessionFactory sqlSessionFactory;
//    @PostConstruct
    public void test(){
        // sss
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TalkUserMapper talkUserMapper = sqlSession.getMapper(TalkUserMapper.class);
        talkUserMapper.selectAll();
        TalkUserBean talkUserBean = talkUserMapper.selectByName("brink");
        System.out.println(sqlSession);
        System.out.println(talkUserBean);
    }

    private static void someClass(){
        MapperProxy mapperProxy = null;
        SimpleExecutor simpleExecutor;
        BoundSql boundSql;
        SqlSession sqlSession;
    }

    /**
     *
     */
    private static void buzhou(){

    }
}
