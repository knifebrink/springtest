package com.spring.test.sourcetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author brink
 * 2021/7/5 15:47
 */
public class IocTest1 {
    public static final Logger LOGGER = LoggerFactory.getLogger("fch");
    public static void main(String[] args){
        test1();
//        test2();
//        test3();
//        test4();
    }

    // 使用@bean方法进行装载
    public static void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = applicationContext.getBean(User.class);
        LOGGER.info(user.getId()+" "+user.getName());
    }

    // 使用@compenent 扫描装载
    public static void test2(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig2.class);
        User2 user = applicationContext.getBean(User2.class);
        LOGGER.info(user.getId()+" "+user.getName());
    }
    //@Autowired 依赖注入，自动装载
    public static void test3(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig2.class);
        User3 user = applicationContext.getBean(User3.class);
        user.service();
        LOGGER.info(""+user.getDog());
    }
    //生命周期测试，定义->初始化->生存期->销毁
    /**
     * 开启懒加载模式后，更容易观察周期
     */
    public static void test4(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig4.class);
        System.out.println("这是一个分割线------------");
        User4 user = applicationContext.getBean(User4.class);
        LOGGER.info(user.getId()+" "+user.getName());
    }

}
