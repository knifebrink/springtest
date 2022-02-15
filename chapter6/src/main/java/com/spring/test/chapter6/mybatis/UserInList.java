package com.spring.test.chapter6.mybatis;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brink
 * 2021/7/6 11:29
 */
@Data
public class UserInList {
    private String id;
    private int count;
    private String userName;
    private ArrayList<UserMoney2> moneyList;
    public UserInList(){}
    public UserInList(String userName,int count){
        this.userName = userName;
        this.count = count;
    }
}
