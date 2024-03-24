package com.hjc.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class HelloController {

    @Resource
    private UserService userService;

    //处理首页或是登录界面跳转

    @PreAuthorize("hasAnyRole('user','admin')")
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PreAuthorize("hasRole('user')")  //直接使用hasRole方法判断是否包含某个角色
    @RequestMapping("/h1")
    @ResponseBody
    public String h1(){
        return "hello1";
    }

    @PostAuthorize("hasAnyRole('user','admin')")
    @RequestMapping("/h2")
    @ResponseBody
    public String h2(){
        System.out.println("执行了");
        return "hello2";
    }

    @RequestMapping("/h3")
    @ResponseBody
    public String h3(){
        System.out.println("正常打印");
        userService.test();
        return "hello3";
    }

    @RequestMapping("/h4")
    @ResponseBody
    public String h4(){
        System.out.println("正常打印");
        userService.test();
        return "hello4";
    }

    @Secured({"ROLE_admin","ROLE_user"})
    @RequestMapping("/h5")
    @ResponseBody
    public String h5(){
        System.out.println("执行了");
        return "hello5";
    }

    @RequestMapping("/h6")
    @ResponseBody
    public String h6(){
        System.out.println("正常打印");
        userService.test(new ArrayList<>(Arrays.asList("zhangsan","lisi","hjc")));
        return "hello6";
    }
    @RequestMapping("/h7")
    @ResponseBody
    public String h7(){
        System.out.println("正常打印");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("zhangsan", "lisi", "hjc"));
        userService.test(list,list);
        return "hello7";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}