package com.atguigu.securitydemo1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //配置类需要重写写一个 配置跳转放行的路径
public class CSRFController {
    @GetMapping("/toupdate")
    public String test(Model model){
        return "csrfTest.html";
    }
    @PostMapping("/update_token")
    public String getToken(){
        return "cssrf_token.html";
    }
}
