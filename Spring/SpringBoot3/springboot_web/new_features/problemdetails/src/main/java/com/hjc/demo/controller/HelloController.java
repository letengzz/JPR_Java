package com.hjc.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /**
     * 使用get请求访问 模拟 请求方式不支持
     * @return
     */
    @PostMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
