package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MapBean {
    @Test
    void testMapBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("map-bean.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println("stu = " + stu);
    }
}
