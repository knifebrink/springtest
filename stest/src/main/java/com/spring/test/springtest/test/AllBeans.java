package com.spring.test.springtest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author brink
 * 2021/8/30 16:11
 * 所有类都在该类下
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AllBeans {
    @Autowired
    public UserP userP;

    @Value("${catalog.name}")
    public String valueTest="";

    @PostConstruct
    public void postConstruct(){
        System.out.println("AllBeans:postConstruct方法:");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("AllBeans:preDestroy方法:");
    }
}
