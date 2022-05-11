package com.spring.test.chapter6.injection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "fch",name = "ccc",havingValue = "aaa") // 当fch.ccc = aaa 的时候才会加载bean
public class Third {
}
