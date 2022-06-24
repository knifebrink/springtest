//package com.spring.test.chapter6.mybatisplus;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.spring.test.chapter6.mybatis.UserMapper2;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author brink
// * 2022-1-21 11:38:41
// * 测试必要：将pom.xml下的mybatis依赖去掉，加入mybatis-plus，测试完后请恢复pom
// */
//@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//@MapperScan("com.spring.test.chapter6.mybatisplus")
//public class MybatisPlusApplication {
//    Logger logger = LoggerFactory.getLogger("fch");
//    public static void main(String[] args){
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(MybatisPlusApplication.class,args);
//
////        applicationContext.stop();
//    }
//
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    UserMapper3 userMapper3;
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    UserMapper4 userMapper4;
//
//    @PostConstruct
//    public void fun(){
//        logger.info("mybatis-plus:继承的查询{}",userMapper3.selectById(3));
////        TestUser testUser = new TestUser("mybatis2",6666);
////        testUser.setId("555");
////        userMapper3.insert(testUser);
//
//        QueryWrapper<TestUser> qryWrapper = new QueryWrapper<>();
//        qryWrapper.eq("userName", "mybatis2");// 查询条件
//        logger.info("条件选择器wrapper语句：{}",qryWrapper.getSqlSegment());
//        qryWrapper.select("userName","count","id"); // select的
//        logger.info("条件选择器wrapper语句2：{}",qryWrapper.getSqlSelect());
//        logger.info("条件选择器wrapper结果：{}",userMapper3.selectList(qryWrapper));;
//
//        // 分页插件查询，跟pageHelper差距不大
//        Page<TestUser> page = new Page<>(2, 2);
//        logger.info("分页查询结果：{} 总长度为{}",userMapper4.selectUser(page),page.getTotal());
//
//
//        // 分页有点奇怪的
//        Page<TestUser> page2 = new Page<>(2, 3);
//        logger.info("分页查询结果：{} 总长度为{}",userMapper4.selectUserLeftJoin3(),page.getTotal());
//    }
//
//    // 增加mybatis-plus的分页插件
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        return interceptor;
//    }
//}
