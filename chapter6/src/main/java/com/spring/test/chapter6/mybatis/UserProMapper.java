package com.spring.test.chapter6.mybatis;

import com.spring.test.chapter6.chapter6.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserProMapper {
    int insertUser(@Param("user")User user);
    int insertUserBatch(@Param("list") List<User> user);
}
