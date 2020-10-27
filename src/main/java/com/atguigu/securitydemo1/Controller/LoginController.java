package com.atguigu.securitydemo1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/userLogin")
    public String login(){
        return "login.html";
    }

}
