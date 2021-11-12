package com.spring.test.springtest.mybatistest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author brink
 * 2020/12/11 15:29
 *
 * 增删改查简单版
 */
@Mapper
@Repository
public interface TalkUserMapper {
    List<TalkUserBean> selectAll();

    Integer insertOne(TalkUserBean talkUserBean);

    Integer deleteByName(String name);

    Integer updateByName(@Param("name")String name, @Param("bean")TalkUserBean talkUserBean);

    Integer countByName(@Param("name")String name);

    TalkUserBean selectByName(@Param("name") String name);

//    Integer count(String name);
}
