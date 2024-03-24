package com.hjc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hjc
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping(value = "/")
    public String hello(){
        return "HelloWorld!";
    }
}