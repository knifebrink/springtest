package com.spring.test.chapter6.chapter10;

import com.spring.test.chapter6.chapter6.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brink
 * 2021/9/9 17:42
 */
@Controller
@RequestMapping("/chapter10")
public class Chapter10Controller {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test1(User user) {

        return "这是user："+user.toString();
    }

    @RequestMapping(value = "/test2")
    @ResponseBody
    public String test2(@RequestBody User user) {

        return "这是user2："+user.toString();
    }

    /***
     * 只是验证，但。。。还是能跑进去数值
     * 解析验证参数错误
     * 验证数据
     * @param vp —— 需要验证的POJO，使用注解@Valid 表示验证
     * @param errors  错误信息，它由Spring MVC通过验证POJO后自动填充
     * @return 错误信息Map
     */
    @RequestMapping(value = "/valid")
    @ResponseBody
    public Map<String, Object> validate(
            @Valid @RequestBody ValidatorPojo vp, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;
            // 字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();// 获取错误验证字段名
            } else {
                // 非字段错误
                key = oe.getObjectName();// 获取验证对象名称
            }
            // 错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        errMap.put("validatorPojo",vp);
        return errMap;
    }
}
