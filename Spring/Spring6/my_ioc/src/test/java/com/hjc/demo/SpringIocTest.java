package com.hjc.demo;

import com.hjc.demo.context.AnnotationApplicationContext;
import com.hjc.demo.context.ApplicationContext;
import com.hjc.demo.service.UserService;
import org.junit.jupiter.api.Test;

public class SpringIocTest {
    @Test
    public void testIoc() {
        ApplicationContext context = new AnnotationApplicationContext("com.hjc.demo");
        UserService userService = (UserService) context.getBean(UserService.class);
        userService.out();
        System.out.println("run success");
    }
}
