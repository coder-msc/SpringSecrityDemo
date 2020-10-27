package com.atguigu.securitydemo1.Controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("hello")
    public String hello(){

        return "hello Spring Security";
    }
    @GetMapping("index")
    public String index(){

        return "hello Spring index";
    }

    @GetMapping("update")
  //  @Secured({"ROLE_sale","ROLE_manager"})
    @PreAuthorize("hasAnyAuthority('admins')")
    public String update(){

        return "hello Spring update";
    }
}
