package com.hjc.demo.controller;

import com.hjc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    //属性注入：
//    @Autowired
//    private UserService userService;

    //set注入：
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    //构造方法注入：
//    private UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //形参上注入：
//    private UserService userService;
//
//    public UserController(@Autowired UserService userService) {
//        this.userService = userService;
//    }
    //只有一个构造函数，无注解：
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //Autowired注解和Qualifier注解联合：

    public void out() {
        userService.out();
        System.out.println("Controller层执行结束。");
    }

}
