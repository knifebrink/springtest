package com.spring.test.chapter6.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author brink
 * 2022-1-21 11:38:36
 */
@Data
public class TestUser {
    private String id;
    private int count;
    @TableField("userName")// 因为开启了驼峰映射
    private String userName;
    public TestUser(){}
    public TestUser(String userName, int count){
        this.userName = userName;
        this.count = count;
    }
}
