package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiSetter {

    private Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

    @Test
    void testDiSetter() {
        ApplicationContext context = new ClassPathXmlApplicationContext("di-setter.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println("stu = " + stu);
    }
}
