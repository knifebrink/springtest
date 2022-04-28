package com.spring.test.chapter6.qovodto.controller;

import com.spring.test.chapter6.qovodto.domain.UserBo;
import com.spring.test.chapter6.qovodto.domain.UserQo;
import com.spring.test.chapter6.qovodto.domain.UserVo;
import com.spring.test.chapter6.qovodto.service.IQoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Vo （View Object） 返回数据层的数据
 * QO (Query object) 查询对象 ，但在第一个方法中，不严格执行其含义，在服务层和Dao层也在使用
 */
@RestController
@RequestMapping("/qovo")
public class QoVoController {
    @Autowired
    IQoVoService qoVoService; // 服务层
    @GetMapping("/find")
    public List<UserVo> findUserList(UserQo qo){
        return qoVoService.findUserList(qo);
    }

    @GetMapping("/insert")
    public Boolean insert(@RequestBody UserBo userBo){
        return qoVoService.insert(userBo);
    }
}
