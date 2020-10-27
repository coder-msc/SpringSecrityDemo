package com.atguigu.securitydemo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userdetailsService;
//注入数据源
    @Autowired
    private DataSource dataSource;
//配置对象
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);//自动创建表
        return jdbcTokenRepository;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userdetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.exceptionHandling().accessDeniedPage("/unauth.html");//403自定义页面
        //退出的地址
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();
        http.formLogin() //自定义登录页面
        .loginPage("/login.html") //登录页面设置
        .loginProcessingUrl("/user/login")// 登录访问路径
        .defaultSuccessUrl("/success.html").permitAll()//登录成功之后跳转路径
        .and()
        .authorizeRequests().antMatchers("/user/login","/","/test/hello").permitAll()//哪些路径不需要认证可直接访问
         //当前登录用户，只有具有admins权限才可以访问这个路径
          //hasAuthority方法
//        .antMatchers("/test/index").hasAuthority("admins")
         //该路径可以被多个角色访问时 使用这个
        .antMatchers("/test/index").hasAnyAuthority("admins,manage")

        .anyRequest().authenticated()
        .and().rememberMe().tokenRepository(persistentTokenRepository())
        .tokenValiditySeconds(60) //设置过期时间
        .userDetailsService(userdetailsService)
        .and().csrf().disable(); //关闭csrf防护,默认开启的
    }

}