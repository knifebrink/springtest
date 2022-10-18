package com.spring.test.springtest.springtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author brink
 * 2021/12/1 9:01
 * 源码查看和分析
 * 怎么开始看，从源头上看起，顺便学英文，会使用大量的注释
 * 回忆一下关键组件，
 * ApplicationContext、BeanFactory、BeanDefinition、 configuration metadata 、
 *
 */
@SpringBootApplication
//@EnableAsync
public class SpringFrameworkTest {
    public static final Logger LOGGER = LoggerFactory.getLogger("fch");
    public static void main(String[] args){
//        testSpring();
//        testSpringSource();
        testSpringThreeSource();
//        testSpringThreeSource2();
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
                new ClassPathXmlApplicationContext("genericApplicationContextSource.xml");
//        classPathXmlApplicationContext.getBean(School.class);
    }

    /**
     * 三级缓存主要是解决循环依赖，和AOP代理自调用
     * 例如例子中
     * 会先创建dad，加入第三缓存
     * 然后填充son时,发现也有dad，从第三缓存中取出，dad加入第二缓存，son填充完毕
     * dad填充son
     * 使用三级而不是两级，主要还解决了aop代理的问题
     * https://blog.csdn.net/chinawangfei/article/details/122963121
     * https://blog.csdn.net/u022812849/article/details/124064630 // Async异常问题
     */
    public static void testSpringThreeSource(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("genericApplicationContextSource2.xml");
        Dad dad = classPathXmlApplicationContext.getBean(Dad.class);
        Son son = classPathXmlApplicationContext.getBean(Son.class);
    }

    /**
     * 开启异步三级缓存也不好使。
     * 主要是因为三级缓存时没有提前准备好Async的代理所产生的，主要是第一个异步造成的
     */
    public static void testSpringThreeSource2(){
        ConfigurableApplicationContext context = SpringApplication.run(SpringFrameworkTest.class);
        Dad dad = context.getBean(Dad.class);
        Son son = context.getBean(Son.class);
        System.out.println(dad+""+son);
    }
}
