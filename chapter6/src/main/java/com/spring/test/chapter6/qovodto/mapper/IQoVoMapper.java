package com.spring.test.chapter6.qovodto.mapper;

import com.spring.test.chapter6.qovodto.domain.UserBo;
import com.spring.test.chapter6.qovodto.domain.UserQo;
import com.spring.test.chapter6.qovodto.domain.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IQoVoMapper {
    List<UserVo> findUserList(@Param("qo") UserQo qo);

    Boolean insert(UserBo userBo);
}
