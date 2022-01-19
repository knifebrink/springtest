package com.spring.test.chapter6.mybatis;

import com.spring.test.chapter6.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author brink
 * 2021/7/6 11:24
 */
@Mapper
public interface UserMapper2 {
    List<User> selectUser();
    int updateUser(User user);
    int insertUser(User user);

    User selectUserById(String id);

    // 使用resultMap
    List<User> selectUserResultMap();

    List<User> selectUserIf(@Param("id") Integer id);

    List<User> selectUserForEach(List<String> list);

    /**
     * 测试sql标签，sql标签有点类似于函数，可复用
     * 只简单测试，可带参数
     */
    List<User> selectUserSqlInclude();


}
