package com.atguigu.securitydemo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//@Configuration //配置类设置密码
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
//    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //加密器 new了对象 所以需要对这个对象动手创建一下
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password=passwordEncoder.encode("123");
        auth.inMemoryAuthentication().withUser("Lucy").password(password).roles("admin");
    }

    @Bean
    PasswordEncoder passwoed(){
        return  new BCryptPasswordEncoder();
    }
}
