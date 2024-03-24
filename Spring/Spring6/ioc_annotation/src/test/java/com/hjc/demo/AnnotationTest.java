package com.hjc.demo;

import com.hjc.demo.config.Spring6Config;
import com.hjc.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationTest {
    private Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void testAllAnnotation(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring6Config.class);
        UserController userController = context.getBean("userController", UserController.class);
        userController.out();
        logger.info("执行成功");
    }
}
