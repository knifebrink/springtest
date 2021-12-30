package com.spring.test.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

/**
 * 用来定义WebSecurityConfigure，此类是一个抽象类，继承即默认方法
 * csrf 防止跨域攻击，好像会将所有post给弹掉，所以前后端分离做不了，原理是post的body中存有_csrf 字符串
 *
 * 整个的默认流程是，浏览器请求/hello，没有登录过的话，跳转到/login，登录成功加cookie并跳转到/hello。使用的是session方案？
 */
@Configuration
//@Order(Integer.MIN_VALUE)
public class Test2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    /**
     * 用于配置用户、角色、加密等
     * @param auth AuthenticationManager构造器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 设置自定义用户服务，和使用密码加密
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        System.out.println("Test2WebSecurityConfigurerAdapter config");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web); // 父类中做了，允许任何角色，以及启用formLogin和httpBasic


    }


    /**
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        configureDefault(http);
//        configureSome(http);
        configureFilter(http);
    }

    // 默认的配置，即抽象父类的方法
    private void configureDefault(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    /**
     * ant配置路径和角色，权限
     * Role 是带"ROLE_"前缀的Authority，前者英文为角色，后者为权限
     */
    private void configureSome(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello/hello").hasAnyRole("ADMIN","TEST") // 两个角色都可以访问
                .antMatchers("/hi/**").hasAuthority("ROLE_TEST") // 要TEST角色才能访问
                .anyRequest().permitAll()  // 允许所有


                //
//                .and().anonymous()
                // 表格形式登录
                .and().formLogin()
                // http Security
                .and().httpBasic()
                .and().csrf().disable()


        ;

    }

    private void configureFilter(HttpSecurity http)  throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello/hello").hasAnyRole("ADMIN","TEST") // 两个角色都可以访问
                .antMatchers("/hi/**").hasAuthority("ROLE_TEST") // 要TEST角色才能访问
                .anyRequest().permitAll()  // 允许所有


                //
//                .and().anonymous()
                // 表格形式登录
                .and().formLogin()
                // http Security
                .and().httpBasic()
                .and().csrf().disable()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(restAuthenticationEntryPoint)
//                .authenticationEntryPoint(restAuthenticationEntryPoint())



        ;

    }

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    RestfulAccessDeniedHandler restAuthenticationEntryPoint;
    @PostConstruct
    public void fun(){


    }
}
