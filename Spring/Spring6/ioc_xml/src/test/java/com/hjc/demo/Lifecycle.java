package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lifecycle {
    @Test
    void testLife() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
        User bean = ac.getBean(User.class);
        System.out.println("生命周期：4、通过IOC容器获取bean并使用");
        ac.close(); //销毁容器
    }
}
