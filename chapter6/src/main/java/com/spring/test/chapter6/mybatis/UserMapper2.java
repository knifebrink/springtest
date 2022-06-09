package com.spring.test.chapter6.mybatis;

import com.spring.test.chapter6.chapter6.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 测试sql标签，sql标签有点类似于函数，可复用
     * 只简单测试，可带参数
     */
    List<User> selectUserSqlIncludeWithParams(User user);

    /**
     * 测试sql标签，sql标签有点类似于函数，可复用
     * 带参数
     */
    List<User> selectUserSqlIncludeWithParams2(User user);

    /**
     * collection测试
     * 主要是主对多的查询，例如主表里包含一个List，不需要写list的查询语句，只建立映射即可
     * @return
     * https://blog.csdn.net/lzxomg/article/details/89739651
     * https://blog.csdn.net/minpann/article/details/51217106
     */
    List<UserInList> selectUserTestCollection();

    /**
     * 一对多，多list的情况
     * @return
     */
    List<UserInList> selectUserTestCollection2();

    /**
     * trim测试 主要是拼接sql，有点类似单词的foreach，常用于插入
     * https://blog.csdn.net/wt_better/article/details/80992014
     */
    Integer insertUserTestTrim(User user);

    List<User> selectUserChooseWhen(@Param("id") Integer id);

    Integer countTestLongSql(List<Long> list);

}
