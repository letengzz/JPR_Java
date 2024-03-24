package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeBean {
    @Test
    public void testBeanScope(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-scope.xml");
        User user1 = ac.getBean(User.class);
        User user2 = ac.getBean(User.class);
        System.out.println(user1==user2);
    }
}
