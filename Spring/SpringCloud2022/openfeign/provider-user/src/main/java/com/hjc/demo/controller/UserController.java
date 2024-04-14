package com.hjc.demo.controller;


import com.hjc.demo.entity.User;
import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjc
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
}
