package com.spring.test.chapter6;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author brink
 * 2021/7/6 11:24
 */
@Mapper
public interface UserMapper {
    List<User> selectUser();
    int updateUser(User user);
    int insertUser(User user);

    User selectUserById(String id);
}
