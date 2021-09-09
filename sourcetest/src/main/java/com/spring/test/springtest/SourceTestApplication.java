package com.spring.test.springtest;

//import com.spring.test.sourcetest.controller.TestController;
import com.spring.test.springtest.controller.User;
import org.springframework.asm.ClassReader;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.support.*;
import org.springframework.context.annotation.*;
import org.springframework.core.SpringVersion;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.*;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SourceTestApplication {

	/**
	 * 顺着debug去看源码，比较大的缺点是并非是抽象和面向对象的思维
	 * 如何把一个user加入到ioc容器
	 * 1. {@link SpringApplication#run(Class, String...)} 开始
	 * 2. {@link ServletWebServerApplicationContext#refresh() }
	 * 3. {@link AbstractApplicationContext#refresh()}  }// 加载或刷新持久层配置
	 * 3.5 {@link AbstractApplicationContext#finishBeanFactoryInitialization}  }// 实例化非懒加载单例
	 * 4. {@link DefaultListableBeanFactory#preInstantiateSingletons()}//实例化非懒加载单例
	 * 5. {@link AbstractBeanFactory#getBean(String) #doGetBean()} // 获取一个实例
	 * 5. {@link AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)} // 获取一个实例
	 * 6. {@link DefaultSingletonBeanRegistry#getSingleton(String, ObjectFactory)}// 获取一个单例实例，创建和注册（加入集合）
	 * 6. {@link ObjectFactory#getObject()}	//
	 * 7. {@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])} // 创建一个实例
	 * 8. {@link AbstractAutowireCapableBeanFactory#createBeanInstance}	// 根据对象使用不同的策略创建实例
	 * 9. {@link AbstractAutowireCapableBeanFactory#instantiateBean} // 使用默认构造器构造
	 * 10. {@link SimpleInstantiationStrategy#instantiate(RootBeanDefinition, String, BeanFactory)}
	 * 11. {@link org.springframework.beans.BeanUtils#instantiateClass(Constructor, Object...)}
	 * 12. {@link java.lang.reflect.Constructor#newInstance(Object...)} // 反射构造一个实例对象并开始返回
	 * 	 	//逐级返回上一级调用
	 * 13. {@link DefaultSingletonBeanRegistry#getSingleton(String, ObjectFactory)}
	 * 14. {@link DefaultSingletonBeanRegistry#addSingleton(String, Object)} // 注册、加入到单例集合
	 *
	 *
	 * 可以从返回的对象中ApplicationContext获取beanFactory，然后获取装载进去的对象实例
	 * 该当前测试中工厂类为{@link DefaultListableBeanFactory}
	 * 1. getBean(.class)
	 * 2. getBeanNamesForType() 找到名字
	 * 3. 转向父类doGetBean()
	 * 4. DefaultSingletonBeanRegistry中的singletonObjects集合中获取实例bean
	 */

	/**
	 * 扫描注册到，上面的前置
	 * 1. {@link SpringApplication#run}
	 * 2. {@link SpringApplication#refresh}
	 * 3. {@link ServletWebServerApplicationContext#refresh}
	 * 4. {@link AbstractApplicationContext#refresh}
	 * 5. {@link AbstractApplicationContext#invokeBeanFactoryPostProcessors}
	 * 6. {@link PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)}
	 * 7. {@link ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry(BeanDefinitionRegistry)} // 产生更远的bean记录
	 * 8. {@link ConfigurationClassParser#parse(Set)}
	 * 9. {@link ConfigurationClassParser#parse(AnnotationMetadata, String)}
	 * 10. {@link ComponentScanAnnotationParser#parse} // 设置扫描规则，如过滤和需扫描
	 * 11. {@link ClassPathBeanDefinitionScanner#doScan(String...)} // 开始扫描
	 * 12. {@link ClassPathBeanDefinitionScanner#registerBeanDefinition}// 符合规则 注入到registry
	 * 13. {@link BeanDefinitionReaderUtils#registerBeanDefinition}// 符合规则 注入到registry
	 * 14. {@link DefaultListableBeanFactory#registerBeanDefinition}// 继续判断，然后注册在此工厂
	 * end	   beanDefinitionNames.add(beanName)  // 注册到注册集合
	 *
	 */
	/**
	 * 扫描详细，如扫描注解@com
	 * {@link ClassPathScanningCandidateComponentProvider#scanCandidateComponents}// 扫描入口
	 * {@link SimpleMetadataReader} // 其中一个阅读器，提供单个类元数据读取，提供扫描
	 * {@link SimpleAnnotationMetadataReadingVisitor}// 访问ClassReader的访问者
	 * {@link ClassReader} // 根据.class 类结构文件给与访问者一些信息，与上面构成访问者模式，根据Java类字节码规则读取如注解相关的信息
	 * 得到信息后，符合规则，如等于@component则返回真
	 */
	/**
	 *  初始化、配置、组装。依赖注入
	 *
	 */
	/**
	 * 以注解一个User，其他均为默认 测试
	 * spring IOC 依赖注入 总结
	 * 1. 入口，然后进行一系列初始化，如工厂，扫描设置，规则等，在springboot的规则下，已进行了一系列默认规则设置
	 * 2. 以包名为区域对.class文件进行读取元信息
	 * 3. 得到类字节码信息扫描符合条件的bean(如被@component注解的)
	 * 4. 将符合规则的类注册进类定义集合
	 * 5. 根据定义集合，遍历创建对象实例，利用不同的策略进行构造，本例中用默认反射的方式进行实例化对象
	 * 6. 将实例化对象加入到单例集合map。
	 *
	 * 7. 使用时，从单例集合map中取出。
	 *
	 * 当然还有懒加载、其他方式等，因为其spring的发展有更多的设置，后面可以熟悉业务后再看一遍。但
	 * 整体的，可能就这样了。还有xml等方式等。xml构思是，先将xml预先编译成.class。
	 * 还有自动装载，和带自动装载的参数等，挺多的。
	 */
	public static void main(String[] args) {
		System.out.println("Spring V:"+SpringVersion.getVersion());
		System.out.println("SpringBoot V:"+SpringBootVersion.getVersion());
		BeanFactoryPostProcessor a;
		ConfigurableApplicationContext context = SpringApplication.run(SourceTestApplication.class, args);
		System.out.println("超级工厂对象："+context);
		AnnotationConfigServletWebServerApplicationContext aContext = (AnnotationConfigServletWebServerApplicationContext) context;
//		System.out.println("获取的实例对象："+aContext.getBean(TestController.class));
		try {
			System.out.println("获取的实例对象：" + context.getBean(User.class));
		}catch (Exception e){}
		ConfigurableListableBeanFactory beanFactory=context.getBeanFactory();
		DefaultListableBeanFactory aBeanFactory = (DefaultListableBeanFactory) beanFactory;

//		// 显示所有装载的实例名字
//		for (Iterator<String> it = aBeanFactory.getBeanNamesIterator(); it.hasNext(); ) {
//			String name = it.next();
//			System.out.println(name);
//		}

		aBeanFactory.getBean("uss");
		aBeanFactory.getBean(User.class);
		aBeanFactory.getBeanNamesForType(User.class);
		System.out.println(""+	aBeanFactory.getBean("uss"));




//		context.stop();

	}

	private static void test(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(SourceTestApplication.class, args);
		context.getBean("fad",User.class);
	}

}
