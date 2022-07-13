package com.spring.test.chapter6.mybatis;

import lombok.Builder;
import lombok.Data;

@Data
public class UserMoney {
    private int id;
    private int money;
    private int userId;
}
