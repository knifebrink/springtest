package com.spring.test.chapter6.mybatis;

import cn.hutool.core.io.FileUtil;
import com.spring.test.chapter6.chapter6.User;
import com.spring.test.chapter6.redis.RedisApplication;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brink
 * 2022/1/13 15:56
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class MybatisApplication {
    public static void main(String[] args){
        SpringApplication.run(MybatisApplication.class,args);


    }

    @Autowired
    UserMapper2 userMapper2;
    Logger logger = LoggerFactory.getLogger("fch");
    @PostConstruct
    public void fun(){
        System.out.println("success");
//        logger.warn(""+userMapper2.selectUserResultMap());
//        logger.warn("这是测试if标签：{}",userMapper2.selectUserIf(1));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
//        logger.warn("这是测试foreach标签：{}",userMapper2.selectUserForEach(list));
//        logger.warn("这是测试sql标签：{}",userMapper2.selectUserSqlInclude());
//        logger.warn("这是测试collection标签：{}",userMapper2.selectUserTestCollection());
//        logger.warn("这是测试trim标签：{}",userMapper2.insertUserTestTrim(new User("啊啊啊",0)));
//        logger.warn("这是测试chooseWhen标签：{}",userMapper2.selectUserChooseWhen(2));
//        logger.warn("这是测试sql标签 带参数：{}",userMapper2.selectUserSqlIncludeWithParams(new User("aaa",2)));
        logger.warn("这是测试sql标签 带参数2：{}",userMapper2.selectUserSqlIncludeWithParams2(new User("aaa",2)));
//        testLongSql();
        sqlSessionTemplate.getConfiguration().getDefaultExecutorType();
        logger.warn(""+sqlSessionTemplate.getExecutorType());
    }

    private void testLongSql(){
        List<Long> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(long i=0;i<1000000;i++){ // 大概10M的字符
            list.add((long) Integer.MAX_VALUE);
            stringBuilder.append(Integer.MAX_VALUE);
            stringBuilder.append(",");
        }
        logger.warn("这是测试超长sql：{}",userMapper2.countTestLongSql(list));
    }

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
}
