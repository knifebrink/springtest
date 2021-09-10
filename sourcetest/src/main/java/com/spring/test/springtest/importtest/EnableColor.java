package com.spring.test.springtest.importtest;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author brink
 * 2021/9/10 17:12
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({Red.class, ColorRegistrarConfiguration.class, ColorImportSelector.class, ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {
}
