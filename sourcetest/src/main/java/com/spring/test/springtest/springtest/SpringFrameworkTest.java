package com.spring.test.springtest.springtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author brink
 * 2021/12/1 9:01
 * 源码查看和分析
 * 怎么开始看，从源头上看起，顺便学英文，会使用大量的注释
 * 回忆一下关键组件，
 * ApplicationContext、BeanFactory、BeanDefinition、 configuration metadata 、
 *
 */
public class SpringFrameworkTest {
    public static final Logger LOGGER = LoggerFactory.getLogger("fch");
    public static void main(String[] args){
//        testSpring();
        testSpringSource();
    }

    /**
     * 根据以下的测试，不同的applicationContext主要是加载配置文件的方式不一样(如记录bean的.xml文件)
     * 如下，有些不能直接使用.xml，有些不能使用注解，看名字就可以看出了。
     */
    public static void testSpring(){
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        SpringUser user = applicationContext.getBean(SpringUser.class);
        LOGGER.info("ancAC生成的："+user.toString()+"");

        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(genericApplicationContext);
        xmlReader.loadBeanDefinitions(new ClassPathResource("genericApplicationContext.xml"));
        genericApplicationContext.refresh();
        LOGGER.info("genericAC生成的：" + genericApplicationContext.getBean(SpringUser.class).toString());

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("genericApplicationContext.xml");
        LOGGER.info("cXAC生成的："+classPathXmlApplicationContext.getBean(SpringUser.class));


    }

    public static void testSpringOther(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("genericApplicationContextOther.xml");
        LOGGER.info("cXAC生成的："+classPathXmlApplicationContext.getBean(School.class));
        LOGGER.info("cXAC生成的："+classPathXmlApplicationContext.getBean(HighSchool.class));
    }

    public static void testSpringSource(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("genericApplicationContextOther.xml");
        classPathXmlApplicationContext.getBean(School.class);
    }
}
