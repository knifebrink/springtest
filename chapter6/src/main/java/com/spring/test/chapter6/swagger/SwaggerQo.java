package com.spring.test.chapter6.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "swg查询参数")
@Data
public class SwaggerQo {
    @ApiModelProperty(value = "这就是个id")
    Long id;
}
