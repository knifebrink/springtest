package com.spring.test.security;

import com.spring.test.security.config.JwtAuthenticationTokenFilter;
import com.spring.test.security.config.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * 安全测试
 * 只要pom.xml中加了spring security依赖，就会自动开启防护
 * 原理主要是用Servlet Filter
 * 会在日志中生成密码，用户名是user
 * 重要的类
 * {@link WebSecurityConfigurerAdapter} 主要管理配置
 * {@link UserDetailsService}   用户配置接口
 * {@link UserDetails} 用户
 * {@link HttpSecurity} 配置对象
 * {@link Authentication} 存储的授权信息
 * {@link SecurityContext} 获取上下文
 */
@SpringBootApplication
public class SecurityApplication {
    public static final Logger LOGGER = LoggerFactory.getLogger("fch");
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @PostConstruct
    public void fun(){
//        System.out.println("这是服务："+userDetailsService);
    }

    /**
     * spring security流程
     * 验权流程
     * 1. 应该是从{@link OncePerRequestFilter}开始，最初的springFilter，但是个抽象类。。
     * 2. 然后是一堆的动态代理和过滤链
     * 3. 然后是设置的FilterBefore，如本例子的{@link JwtAuthenticationTokenFilter}，作为验权前的处理
     * 4. 然后同样是{@link FilterSecurityInterceptor}
     * 5. {@link AbstractSecurityInterceptor#attemptAuthorization} 进行验权
     * 6. {@link AffirmativeBased#decide} 紧接着被调用验权
     * 7. 验权成功则跳过，否则报错
     * 8. 验权成功进入{@link DispatcherServlet} 就是走进spring MVC流程了
     * 9. 进入Controller的方法
     *
     * form登录授权流程
     * 先进行验权流程，然后失败，跳转到/login 登录页面
     * 1. 请求/hello
     * 2. 根据cookie和保存的session 进入验权流程
     * 3. 验权成功则成功，失败下一步
     * 4. 验权失败，跳转到/login 登录页面
     * 5. 然后是设置的FilterBefore，作为验权前的处理
     * 6. {@link AbstractAuthenticationProcessingFilter#attemptAuthentication} 尝试认证
     * 7. {@link UserDetailsService#loadUserByUsername} 获取用户信息
     * 8. 验权保存相应的信息和返回cookie，跳转回本来请求的页面如/hello
     * 9. 走验权流程
     * 10. 返回/hello的请求
     *
     *
     * 重要的类
     * {@link FilterChainProxy} 经常出现
     */
    private void onlyFun(){
        AbstractAuthenticationProcessingFilter o;
    }
}

