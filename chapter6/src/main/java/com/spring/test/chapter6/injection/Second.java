package com.spring.test.chapter6.injection;

import com.spring.test.chapter6.chapter6.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnClass(name = "com.spring.test.chapter6.injection.Four") // 只有当有Four类的时候才会注入
public class Second {
}
