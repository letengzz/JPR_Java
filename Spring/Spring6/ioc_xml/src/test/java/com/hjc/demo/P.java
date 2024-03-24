package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class P {
    @Test
    void testP() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("p-bean.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println("stu = " + stu);
    }
}
