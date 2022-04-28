package com.spring.test.chapter6.qovodto;

import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import org.springframework.boot.SpringApplication;

/**
 * 三层结构
 * 企业项目中经常需要建立Bean，而有时候经常要建立.Qo，.Vo等后缀的Bean作为不同业务和作用区分
 * vo、qo、dto、bo
 * https://blog.csdn.net/rogerjava/article/details/23748751
 * {@link com.spring.test.chapter6.qovodto.controller.QoVoController}
 */
@SpringBootApplicationNoDataSources
public class BeanApplication {
    public static void main(String[] args){
        SpringApplication.run(BeanApplication.class);
    }
}
