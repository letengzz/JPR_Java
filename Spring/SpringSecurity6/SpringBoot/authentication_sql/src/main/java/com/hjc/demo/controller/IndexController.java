package com.hjc.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hjc
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

//    @Resource
//    private UserDetailsManager manager;

//    @Resource
//    private PasswordEncoder encoder;
//
//    @ResponseBody
//    @PostMapping("/change-password")
//    public JSONObject changePassword(@RequestParam String oldPassword,
//                                     @RequestParam String newPassword) {
//        manager.changePassword(oldPassword, encoder.encode(newPassword));
//        JSONObject object = new JSONObject();
//        object.put("success", true);
//        return object;
//    }
}