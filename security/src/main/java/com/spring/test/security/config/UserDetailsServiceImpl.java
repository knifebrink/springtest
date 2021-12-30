package com.spring.test.security.config;

import com.spring.test.security.SecurityApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brink
 * 2021/12/29 11:02
 *
 * 定义用户的接口
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 在一个新用户请求时，会进行调用
     * 用户可以根据请求的用户名称username，进行用户的构建
     * 例如如下就表示，构建任何用户，角色名为anyName
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityApplication.LOGGER.info("loadUserByUsername: userName: "+username);
        return loadTwoUser(username);
    }

    // 构建任何用户
    public UserDetails loadAnyUser(String username){
        // 角色列表(或权限)
        List<GrantedAuthority> authorityList =  new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("anyName");
        authorityList.add(grantedAuthority);
        //
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 构建用户
        UserDetails userDetails = new User(username,passwordEncoder.encode("1234567"),authorityList);
        return  userDetails;
    }

    // 构建两个用户
    public UserDetails loadTwoUser(String username){
        if(username==null)
            throw new UsernameNotFoundException("not name");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<GrantedAuthority> authorityList =  new ArrayList<>();
        if(username.equals("myuser")) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_TEST");
            authorityList.add(grantedAuthority);
            UserDetails userDetails = new User(username, passwordEncoder.encode("123456"), authorityList);
            return userDetails;
        } else if(username.equals("brink")){ // 创建一个brink用户，密码abc，角色admin
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorityList.add(grantedAuthority);
            UserDetails userDetails = new User(username,passwordEncoder.encode("abc"),authorityList);
            return userDetails;
        }
        // 不符合的直接扔出错误，而不能直接返回null
        throw new UsernameNotFoundException("not name");


    }
}
