package com.spring.test.chapter6.interesting;

import com.spring.test.chapter6.interesting.circledi.Father;
import com.spring.test.chapter6.interesting.circledi.Mother;
import com.spring.test.chapter6.interesting.circledi.ASonP;
import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

/**
 * 在一个项目中发现同时循环遍历时，使用了@Async对象有问题，最有意思的是，在本机环境是没问题的，在线上环境是有问题的。
 *
 *
 * 又试了一下加载bean的顺序， @Order是无效的，跟属性的位置是有关的
 * 有空捋一捋这部分，也能作为一个面试题
 */
@SpringBootApplicationNoDataSources
@Slf4j
@EnableAsync
public class InterestingApplication {

    public static void main(String[] args){
        SpringApplication.run(InterestingApplication.class,args);
    }

    @Autowired
    private ASonP son;// 必须要先于后面的属性
    @Autowired
    private Mother mother;
    @Autowired
    private Father father;

    @PostConstruct
    public void fun(){
        log.warn("循环依赖测试：{} \n,{} \n ,{} \n ",father.getClass(),mother.getClass(),son.getClass());
        father.mother.name = "爸爸给你设置的名字";
        log.warn("循环依赖测试：{},{}",father.name,mother.name);

    }
}
