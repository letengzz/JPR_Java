package com.hjc.demo.controller;

import com.hjc.demo.component.MyHahaComponent;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计Controller调用component 多少次
 * @author hjc
 */
@RestController
public class HelloController {
    @Resource
    private MyHahaComponent component;

    @GetMapping("/hello")
    public String hello() {
        //业务调用 打印hello
        component.hello();
        return "哈哈哈";
    }
}
