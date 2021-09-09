package com.spring.test.chapter6.chapter10;

/**
 * @author brink
 * 2021/9/9 17:43
 */
/**** imports ****/

import com.spring.test.chapter6.User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义字符串用户转换器
 */
@Component
public class StringToUserConverter implements Converter<String,User> {
    @Override
    public User convert(String userStr) {
        User user = new User();
        String[] strArr = userStr.split("-");
        Long id = Long.parseLong(strArr[0]);
        String userName = strArr[1];
        String note = strArr[2];
        user.setId("加入了转换的id:"+id);
        user.setUserName(userName);
        return user;
    }
}
