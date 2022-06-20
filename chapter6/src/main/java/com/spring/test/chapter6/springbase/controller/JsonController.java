package com.spring.test.chapter6.springbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.chapter6.springbase.model.SpringBaseModel;
import com.spring.test.chapter6.springbase.model.SpringJsonModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author andgo
 * @date 2022/6/20
 * @DateTimeFormat 主要是用于参数的入参，因为没有经过json，如果是纯json的话，可以@JsonFormat即可，而且获取的其实是默认的时区
 */
@Slf4j
@RestController
@RequestMapping("/json")
public class JsonController {
    @RequestMapping("/getModel")
    @ResponseBody // 转换成Json
    public SpringJsonModel getBaseModel( SpringJsonModel input){
        SpringJsonModel model = new SpringJsonModel();
        model.setDate(new Date());
        model.setLocalDate(LocalDate.now());
        model.setLocalDateTime(LocalDateTime.now());
        log.warn("时区:{}", TimeZone.getDefault());
        log.warn("入参:{}",input);
        return model;
    }

    @Autowired
    ObjectMapper objectMapper;
    @RequestMapping("/test2")
    @ResponseBody // 转换成Json
    public SpringJsonModel test2(){
        log.warn("objMer {}",objectMapper);
        return new SpringJsonModel();
    }
}
