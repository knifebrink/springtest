package com.spring.test.chapter6.qovodto.service.impl;

import com.spring.test.chapter6.qovodto.domain.UserBo;
import com.spring.test.chapter6.qovodto.domain.UserQo;
import com.spring.test.chapter6.qovodto.domain.UserVo;
import com.spring.test.chapter6.qovodto.mapper.IQoVoMapper;
import com.spring.test.chapter6.qovodto.service.IQoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QoVoServiceImpl implements IQoVoService {
    @Autowired
    IQoVoMapper qoVoMapper;
    @Override
    public List<UserVo> findUserList(UserQo qo) {
        return qoVoMapper.findUserList(qo);
    }

    @Override
    public Boolean insert(UserBo userBo) {

        return qoVoMapper.insert(userBo);
    }
}
