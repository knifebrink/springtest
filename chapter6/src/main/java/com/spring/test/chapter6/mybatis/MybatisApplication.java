package com.spring.test.chapter6.mybatis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        // 需要进行一定延时，插件才生效
        testPageHelper();
        // 一对多对象的时候还是比较容易出错的，有可能会在其他列表中产生脏数据
//        logger.warn("这是测试多collection标签：{}",JSONUtil.toJsonStr(userMapper2.selectUserTestCollection2()));
//        logger.warn("这是测试多collection标签：{}",JSONUtil.toJsonStr(userMapper2.selectTestCount("1","2")));

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

//     分页插件测试，需要延迟执行
    private void testPageHelper(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    int pageNum = 1;
                    int pageSize = 2;
                    PageHelper.startPage(pageNum,pageSize);
                    List<User> userList = userMapper2.selectUser();
                    long total = new PageInfo<>(userList).getTotal();
                    logger.warn("这是测试pageHelper分页插件: 总数为 {} ，{}",total,userList);
                } catch (InterruptedException ignore) {
                }

            }
        }).start();

    }

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
}
