package com.spring.test.chapter6.mybatis;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author andgo
 * @date 2022/7/13
 * 类型转换器
 * 可以做成泛型的
 */
@Component
public class UserThreeTypeHandler extends BaseTypeHandler<UserMoney> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserMoney parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public UserMoney getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return JSON.parseObject(str,UserMoney.class);
    }

    @Override
    public UserMoney getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        return JSON.parseObject(str,UserMoney.class);
    }

    @Override
    public UserMoney getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        return JSON.parseObject(str,UserMoney.class);
    }

    private void logResultSet(ResultSet rs){

    }
}
