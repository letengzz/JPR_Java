package com.hjc.demo;

import com.hjc.demo.context.AnnotationApplicationContext;
import com.hjc.demo.context.ApplicationContext;
import com.hjc.demo.service.UserService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationApplicationContext("com.hjc.demo");
        UserService userService = (UserService) context.getBean(UserService.class);
        userService.out();
    }
}
