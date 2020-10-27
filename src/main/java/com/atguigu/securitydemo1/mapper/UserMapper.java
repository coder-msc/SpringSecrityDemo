package com.atguigu.securitydemo1.mapper;

import com.atguigu.securitydemo1.entity.Myusers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository  //不加这个也没事  这个没有实现类，因为BaseMapper已经帮我们实现了
public interface UserMapper extends BaseMapper<Myusers> {

}
