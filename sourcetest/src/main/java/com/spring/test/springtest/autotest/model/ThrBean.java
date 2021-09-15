package com.spring.test.springtest.autotest.model;

import com.spring.test.springtest.autotest.model.sub.AAAABean;
import com.spring.test.springtest.autotest.model.sub.AAAAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author brink
 * 2021/9/14 11:26
 */
@Component
public class ThrBean {
    public static ThrBean tttt;
//    @Autowired
    AAAAI aaaaBean;
    public ThrBean(){
        System.out.println("初始化ThrBean："+aaaaBean);
        tttt = this;
    }
}
