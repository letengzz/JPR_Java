package com.hjc.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController 就是Controller 和ResponseBody的合体
 * @author hjc
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String handle01() {
        return "Hello, Spring Boot!";
    }
}