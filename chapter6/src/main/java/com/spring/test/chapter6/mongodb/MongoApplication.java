package com.spring.test.chapter6.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * mongoDb测试
 * 两种方式，一种是获取模板接口
 * 另一种是使用JPA的方式{@link MongoUserRepository}
 * 特点，应该是分布式吧。
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.spring.test.chapter6.mongodb")
public class MongoApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(MongoApplication.class,args);
        MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
        save(mongoTemplate);
        query(mongoTemplate);
        queryByJPA(context);

    }
    // JPA查询
    private static void queryByJPA(ConfigurableApplicationContext context){
        MongoUserRepository userRepository = context.getBean(MongoUserRepository.class);
        List<MongoUser> list = userRepository.findByUserNameLike("这是一个mongoDB测试");
        System.out.println("JPA查询："+list.get(0));
    }
    // 增加记录测试
    private static void save(MongoTemplate mongoTemplate){
        MongoUser user = new MongoUser();
        user.setId(31L);
        user.setUserName("这是一个mongoDB测试");
        mongoTemplate.save(user,"mongoUser");
    }

    // 查询
    private static void query(MongoTemplate mongoTemplate){
        System.out.println(mongoTemplate.getCollectionName(MongoUser.class));
        MongoUser mongoUserResult =mongoTemplate.findById(31,MongoUser.class,"mongoUser");
        System.out.println("查询"+mongoUserResult);
    }

    // 删改：略
}
