package com.spring.test.springtest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * 只用来注释
 */
public class ReadMe {

    /**
     * IoC 原理核心关键类
     * {@link DefaultSingletonBeanRegistry}  BeanRegistry bean存储仓库，核心是一个map
     * {@link AbstractApplicationContext} &{@link ApplicationContext} 上下文，使用最多的一个吧。使用模板方法进行的处理。bean工厂的高级接口
     * {@link RootBeanDefinition} && {@link BeanDefinition} bean定义。存储描述了bean的信息，为后续初始化提供重要信息
     * {@link AbstractAutowireCapableBeanFactory} && {@link BeanFactory} bean工厂。实际管理bean的逻辑主力。包括生产bean和获取bean。
     *
     * 有点复杂，既有继承关系，也有组合关系
     *
     */
    private void fun(){
        DefaultSingletonBeanRegistry beanRegistry; // bean存储仓库
        ApplicationContext applicationContext;
        BeanDefinition beanDefinition;
        AbstractAutowireCapableBeanFactory abstractAutowireCapableBeanFactory;
        BeanFactory beanFactory;
    }
}
