package com.hjc.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Resource
    private UserDetailsManager manager;

    @Resource
    private PasswordEncoder encoder;

    @ResponseBody
    @PostMapping("/change-password")
    public JSONObject changePassword(@RequestParam String oldPassword,
                                     @RequestParam String newPassword) {
        manager.changePassword(oldPassword, encoder.encode(newPassword));
        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }

    //处理首页或是登录界面跳转
    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @ResponseBody
    @RequestMapping("/pay")
    public JSONObject pay(@RequestParam String account) {
        JSONObject object = new JSONObject();
        System.out.println("转账给" + account + "成功，交易已完成！");
        object.put("success", true);
        return object;
    }
}