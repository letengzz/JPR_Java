package com.hjc.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hjc
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "user";
    }

    /**
     * 添加用户
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("/add")
    @ResponseBody
    public JSONObject add(@RequestParam String username,
                          @RequestParam String password) {
        userService.addUser(username, password);
        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }


    @PostMapping("/del")
    @ResponseBody
    public JSONObject del(@RequestParam String username) {
        userService.delUser(username);
        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }


    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam String username,
                             @RequestParam String password) {

        JSONObject object = new JSONObject();
        if (userService.updateUser(username, password)) {
            object.put("success", true);
        }else {
            object.put("success", false);
        }
        return object;
    }


    @PostMapping("/updatePass")
    @ResponseBody
    public JSONObject updatePass(@RequestParam String oldPassword,
                                 @RequestParam String newPassword) {

        userService.updatePass(oldPassword, newPassword);
        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }


    @PostMapping("/exists")
    @ResponseBody
    public JSONObject exists(@RequestParam String username) {
        JSONObject object = new JSONObject();
        if (userService.existsUser(username)) {
            object.put("success", true);
        } else {
            object.put("success", false);
        }
        return object;
    }

}
