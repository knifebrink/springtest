package com.spring.test.chapter6.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "swg输出参数")
@Data
public class SwaggerVo {
    @ApiModelProperty(value = "这是swg 输出id")
    Long id;
}
