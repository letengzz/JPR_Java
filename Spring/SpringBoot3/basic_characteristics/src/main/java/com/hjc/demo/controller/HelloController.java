package com.hjc.demo.controller;



import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjc
 */
@RestController
@Slf4j
public class HelloController {

    @Value("${app.description}")
    private String description;

    @RequestMapping("/app")
    public String app(){
        return description;
    }
    @RequestMapping("/hello")
    public String hello(String a,String b) {
        log.info("Hello, Spring Boot!");
        // 打印参数写法
        log.info("参数a:{} ,参数b:{}",a,b);
        return "Hello, Spring Boot!";
    }
}
