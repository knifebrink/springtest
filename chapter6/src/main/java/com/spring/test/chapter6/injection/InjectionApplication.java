package com.spring.test.chapter6.injection;

import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import org.springframework.boot.SpringApplication;

/**
 * \@Conditional(TestCondition.class)
 *
 * 这句代码可以标注在类上面，表示该类下面的所有@Bean都会启用配置，也可以标注在方法上面，只是对该方法启用配置。
 *
 * \@ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
 * \@ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
 * \@ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
 * \@ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
 * \@ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
 * \@ConditionalOnNotWebApplication（不是web应用）
 *
 * ————————————————
 * 原文链接：
 * https://blog.csdn.net/lbh199466/article/details/88303897
 * https://blog.csdn.net/sqlgao22/article/details/96476754
 * https://cloud.tencent.com/developer/article/1836641
 */
@SpringBootApplicationNoDataSources
public class InjectionApplication {
    public static void main(String[] args){
        SpringApplication.run(InjectionApplication.class);
    }
}
