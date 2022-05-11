package com.spring.test.chapter6.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
@Api(tags = "测试swg")
public class Knife4jTestController {

    @ApiOperation("测试swg接口")
    @GetMapping("/test")
    public SwaggerVo testKnife(SwaggerQo qo){
        return new SwaggerVo();
    }
}
