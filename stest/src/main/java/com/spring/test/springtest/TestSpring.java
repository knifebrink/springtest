package com.spring.test.springtest;

import com.spring.test.springtest.test.User;

import com.spring.test.springtest.test.UserSub1;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author brink
 * 2021/9/7 15:52
 */
@SpringBootTest// 我看不懂，但我大受震撼，应该是启动了springboot，不然日志太烦人了
public class TestSpring {
    public static final Logger LOGGER = LoggerFactory.getLogger("fch");

    // 测试看看不同的ApplicationContext接口实例有什么区别
    /**
     * 根据以下的测试
     */
    @Test
    public void testSpring(){
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(genericApplicationContext);
        xmlReader.loadBeanDefinitions(new ClassPathResource("genericApplicationContext.xml"));
        genericApplicationContext.refresh();
        LOGGER.info("genericAC生成的：" + genericApplicationContext.getBean(User.class).toString());

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = applicationContext.getBean(User.class);
        LOGGER.info("ancAC生成的："+user.toString()+"");



    }

    public static void main(String[] args){


    }



}
