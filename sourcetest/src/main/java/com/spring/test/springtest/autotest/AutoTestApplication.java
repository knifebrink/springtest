package com.spring.test.springtest.autotest;


import com.spring.test.springtest.autotest.model.ComScanTest;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.admin.SpringApplicationAdminMXBeanRegistrar;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author brink
 * 2021/9/10 19:14
 * springboot源码查看
 * 参考：https://juejin.cn/book/6844733814560784397/section/6844733814602727437
 * 最主要是利用@Import、ImportBeanDefinitionRegistrar、ImportSelector
 * {@link @ComponentScan} 默认值是从根目录开始扫描标记了的
 * 主要查看两个注解。{@link AutoConfigurationPackage},和@Import(AutoConfigurationImportSelector.class)
 * 一个的作用是 将注解所在包名及子级作为扫描根目录 注入到容器，给与其他框架如(mybatis)保持默认扫描目录一致
 * 另一个的作用是读取包里的spring.factories 类路径，并将其注入到工厂，并且应该都是@Configuration，然后会继续进行加载。
 * 例如{AutoConfigurationImportSelector} 下根据spring.factories返回全限定类名注册到工厂，包括{DispatcherServletAutoConfiguration}，
 * 然后其实用@Bean，依赖注入{@link DispatcherServlet}。
 * 注：spring.factories 可以分散在其他jar，mybatis就是这样
 * XXXAutoConfiguration
 *
 * 以mybatis为例，自动装配是{@link MybatisAutoConfiguration}，然后再.jar包下有，spring.factories，所以会装载MybatisAutoConfiguration，
 * 一般使用@MapperScan进行配置，如果没有的话，MybatisAutoConfiguration条件装载相关的配置，达到自动配置，且有手动配置时，就手动。
 *
 * Spring 确实有点意思
 */

@SpringBootApplication
public class AutoTestApplication {
    public static void main(String[] args){
//        AnnotationConfigServletWebServerApplicationContext applicationContext = new AnnotationConfigServletWebServerApplicationContext(AutoTestApplication.class);
        // 154
        // 160
        ConfigurableWebApplicationContext context =
                (ConfigurableWebApplicationContext) SpringApplication.run(AutoTestApplication.class,args);
//        System.out.println(context.getBean(APerson.class).numBean);
        System.out.println(context.getBean(SpringApplicationAdminMXBeanRegistrar.class));
        System.out.println(context.getBean(DispatcherServlet.class));
        int i=0;
        for (Iterator<String> it = context.getBeanFactory().getBeanNamesIterator(); it.hasNext(); ) {
            String name = it.next();
            i++;

        }
        System.out.println(context);
        context.stop();
    }

    private void fun(){
//        MybatisAutoConfiguration.AutoConfiguredMapperScannerRegistrar autoConfiguredMapperScannerRegistrar;
    }

    private static void fff(){

        try {
            Enumeration<URL> urls =           ClassLoader.getSystemClassLoader().getResources( "META-INF/spring.factories");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
