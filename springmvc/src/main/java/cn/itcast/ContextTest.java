package cn.itcast;

import cn.itcast.controller.HelloSsmController;
import cn.itcast.dao.ItemsMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author brink
 * 2021/9/2 17:05
 * 这个用于测试容器，
 * 至此，我才明白，，，为啥会这样/捂脸
 * spring就仅仅是一个IOC容器，因为跟springMVC和Mybatis集成了一起，所以有些东西看不懂了。beans中的配置，都是方便集成mybatis，其实用程序也是也可以的
 */
public class ContextTest {
    public static void main(String[] args){
        System.out.println("start");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml","spring-mvc.xml");
        System.out.println("测试容器类："+applicationContext.getBean(HelloSsmController.class));
        System.out.println("测试容器类："+applicationContext.getBean(ItemsMapper.class));
    }
}
