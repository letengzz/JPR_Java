package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBean {
    @Test
    public void testUserFactoryBean(){
        //获取IOC容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("factory-bean.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
    }
}
