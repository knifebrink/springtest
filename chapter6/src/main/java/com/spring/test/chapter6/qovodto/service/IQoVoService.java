package com.spring.test.chapter6.qovodto.service;

import com.spring.test.chapter6.qovodto.domain.UserBo;
import com.spring.test.chapter6.qovodto.domain.UserQo;
import com.spring.test.chapter6.qovodto.domain.UserVo;

import java.util.List;

public interface IQoVoService {
    List<UserVo> findUserList(UserQo qo);

    Boolean insert(UserBo userBo);
}
