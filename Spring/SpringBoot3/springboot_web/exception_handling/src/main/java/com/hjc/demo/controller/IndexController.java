package com.hjc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("我是处理！");
        if(true) throw new RuntimeException("您的氪金力度不足，无法访问！");
        return "index";
    }
}
