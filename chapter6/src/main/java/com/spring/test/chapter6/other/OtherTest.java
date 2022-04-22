package com.spring.test.chapter6.other;

import com.spring.test.chapter6.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 其他依稀小工具的测试
 */
public class OtherTest {

    /**
     * jwt测试，jwt是一种存储格式，可以用于登录验权，相比于token是自带了信息
     */
    @Test
    public  void jwtTest(){
        String secret = "knifebrink";
        Long expiration = 60L;
        String tokenHead = "bearer";
        Map<String,Object> map = new HashMap<>();
        map.put("sub","username");
        map.put("created",new Date());
         // 生成jwt
        String genJwtStr =  Jwts.builder()
            .setClaims(map)
            .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
        Utils.log("生成的jwt：" + genJwtStr);

        // 解析jwt
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(genJwtStr);
        Claims claims = claimsJws.getBody();
        Utils.log("jwt解析 内容： "+claims);
        Utils.log("jwt解析 头和签名："+" "+claimsJws.getHeader()+" "+claimsJws.getSignature());
    }
}
