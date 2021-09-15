package com.spring.test.springtest.autotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan(basePackages={"com.brink.note.mybatis.rainbow","com.brink.note.mybatis.mall.mapper"})
public class MyBatisConfig {
}
