package com.yunhe.config;

import com.yunhe.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * @author 无意
 * @description 功能描述
 * @create 2023/11/4/004 15:37
 */
@EnableGlobalMethodSecurity(jsr250Enabled = true)   //开启jsr250注解
//@EnableGlobalMethodSecurity(securedEnabled = true)  //开启secured注解
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Resource(name = "userService")
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义表单登录页面
        http.formLogin()
                //指定登录页面
                .loginPage("/to/login")
                //指定登录请求
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/to/index")
                .failureUrl("/to/failer")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/to/login")
                .invalidateHttpSession(true) //是否清除session
                .and()
                //权限配置
                .authorizeRequests()
                //放行 登录页面
                .antMatchers("/to/login","/to/failer").permitAll()
                //放开 静态资源
                .antMatchers("/css/**","/img/**","/js/**","/plugins/**").permitAll()
                //其他 资源需要登录后访问
                .anyRequest().authenticated()
                .and()
                //禁用csrf
                .csrf().disable();
        //没有权限
        http.exceptionHandling().accessDeniedPage("/to/403");
    }

    //认证的数据需要使用自定义的UserDetailsService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    // 密码加密算法
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
