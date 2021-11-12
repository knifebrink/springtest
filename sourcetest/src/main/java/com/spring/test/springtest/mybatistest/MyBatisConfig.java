package com.spring.test.springtest.mybatistest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 * 配置mybatis配置
 * 如果没有，会自动配置，即扫描springbootApplication所在的根目录及子目录
 */
@Configuration
@MapperScan(basePackages={"com.spring.test.springtest.mybatistest"})
public class MyBatisConfig {
}
