package com.hjc.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjc
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "Hello World HAHA";
    }
}
