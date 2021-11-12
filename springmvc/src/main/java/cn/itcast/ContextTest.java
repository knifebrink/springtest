package cn.itcast;

import cn.itcast.controller.HelloSsmController;
import cn.itcast.controller.ItemsController;
import cn.itcast.dao.ItemsMapper;
import cn.itcast.dao.SayingXmlMapper;
import cn.itcast.domain.Items;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.logging.Logger;

/**
 * @author brink
 * 2021/9/2 17:05
 * 这个用于测试容器，
 * 至此，我才明白，，，为啥会这样/捂脸
 * spring就仅仅是一个IOC容器，因为跟springMVC和Mybatis集成了一起，所以有些东西看不懂了。beans中的配置，都是方便集成mybatis，其实用程序也是也可以的
 * 部署：编译项目，使用maven的 war:war插件打包成.war，放到tomcat网页目录下，启动tomcat
 *
 *
 */
public class ContextTest {
    Logger logger = Logger.getLogger("fch");
    public static void main(String[] args){
        System.out.println("start");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml","spring-mvc.xml");
        System.out.println("测试容器类："+applicationContext.getBean(HelloSsmController.class));
        System.out.println("测试容器类："+applicationContext.getBean(ItemsMapper.class));
        ItemsController itemsController = applicationContext.getBean(ItemsController.class);
        System.out.println("尝试调用接口："+itemsController.list(new BindingAwareModelMap()));
//        ItemsMapper itemsMapper = applicationContext.getBean(ItemsMapper.class);
//        Items items = new Items();
//        items.setId(5);
//        items.setDetail("123");
//        itemsMapper.insert(items);
//        SayingXmlMapper sayingXmlMapper = applicationContext.getBean(SayingXmlMapper.class);
//        sayingXmlMapper.selectAll();
    }
}
