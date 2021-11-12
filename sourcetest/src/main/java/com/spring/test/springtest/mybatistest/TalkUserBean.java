package com.spring.test.springtest.mybatistest;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 账户
 */
@Data
@Component
public class TalkUserBean {
    public TalkUserBean(){
        this.createTime = new Date();
    }
    private String id;
    private String name;
    private String password;
    private Date createTime;
}
