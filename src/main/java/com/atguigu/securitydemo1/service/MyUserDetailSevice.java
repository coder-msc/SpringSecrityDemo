package com.atguigu.securitydemo1.service;

import com.atguigu.securitydemo1.entity.Myusers;
import com.atguigu.securitydemo1.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//@Autowired
//    private UserDetailsService userdetailsService;  将配置类中的对象注入service
@Service("userdetailsService")
public class MyUserDetailSevice implements UserDetailsService {
    @Autowired
    private UserMapper usermapper;

    @Override //这个username就是用户名
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        QueryWrapper<Myusers> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",user_name);
        Myusers users = usermapper.selectOne(wrapper);
        if(users==null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> auths=
                //手动设置这个用户的权限为admins
                AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return  new User(users.getUserName(),
                new BCryptPasswordEncoder().encode(users.getPassword()
        ),auths);
    }
}
