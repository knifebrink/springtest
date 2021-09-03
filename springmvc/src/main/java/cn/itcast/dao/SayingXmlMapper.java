package cn.itcast.dao;


import cn.itcast.domain.SayingBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author brink
 * 2020/9/29 17:13
 */


public interface SayingXmlMapper {

//    int insert(SayingBean sayingBean);

    Integer selectAll();

//    SayingBean getSaying(long id);
}

