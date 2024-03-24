package com.hjc.demo;

import com.hjc.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireTest {
    @Test
    public void testAutoWireByType(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("autowire-byType.xml");
        UserController userController = ac.getBean(UserController.class);
        userController.saveUser();
    }
    @Test
    public void testAutoWireByName(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("autowire-byName.xml");
        UserController userController = ac.getBean(UserController.class);
        userController.saveUser();
    }

}
