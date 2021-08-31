package com.spring.test.springtest;

import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/7/5 16:06
 */
@Component
public class Sub3 {
    private Long id;
    private String name = "这是@Autowired注入的狗";
    public void doSomething(){
        IocTest1.LOGGER.info("狗在吃屎");
    }

    @Override
    public String toString() {
        return "Sub3{" +
                "name='" + name + '\'' +
                '}';
    }
}
