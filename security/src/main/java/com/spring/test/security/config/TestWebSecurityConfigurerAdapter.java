package com.spring.test.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class TestWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("admin")
         // 可通过passwordEncoder.encode("abc")得到加密后的密码
         .password("$2a$10$5OpFvQlTIbM9Bx2pfbKVzurdQXL9zndm1SrAjEkPyIuCcZ7CqR6je")
                .roles("USER","ADMIN")
                .and().
                withUser("myuser") // 注册用户myuser，密码为123456,并赋予ANY_NAME的角色权限
        // 可通过passwordEncoder.encode("123456")得到加密后的密码
                .password("$2a$10$ezW1uns4ZV63FgCLiFHJqOI6oR6jaaPYn33jNrxnkHZ.ayAFmfzLS")
                .roles("ANY_NAME");// 任何名称都可以

        System.out.println("BC加密："+passwordEncoder.encode("abc"));
//        System.out.println(""+auth.getDefaultUserDetailsService().loadUserByUsername("user").getUsername());
//        super.configure(auth);
    }

    @PostConstruct
    public void fun(){


    }
}
