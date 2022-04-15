package com.spring.test.chapter6.springbase.controller;

import com.spring.test.chapter6.springbase.model.SpringBaseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试一下springboot 依赖的json工具。是jackson。
 * 当字段为null时，还传这个字段，这就比较不爽了。都没有，还搁这传
 */
@Controller
@RequestMapping("base")
public class TestController {

    @RequestMapping("getString")
    @ResponseBody // 转换成Json
    public String getString(){
        return "getString";
    }

    @RequestMapping("getBaseModel")
    @ResponseBody // 转换成Json
    public SpringBaseModel getBaseModel(){
        SpringBaseModel springBaseModel = new SpringBaseModel();
        springBaseModel.setId(1);
        springBaseModel.setName("张三");
        return springBaseModel;
    }


}
