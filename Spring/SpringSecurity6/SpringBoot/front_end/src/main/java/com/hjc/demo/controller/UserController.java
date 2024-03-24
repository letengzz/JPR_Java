package com.hjc.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import com.hjc.demo.entity.User;
import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    @RequestMapping("/index")
    public String index() {
        return "user";
    }


    @PostMapping("/add")
    @ResponseBody
    public JSONObject add(@RequestParam String username,
                      @RequestParam String password){
        userService.saveUserDetails(username,password);
        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }

}