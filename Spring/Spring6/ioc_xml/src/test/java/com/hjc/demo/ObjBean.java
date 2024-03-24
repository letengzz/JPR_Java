package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ObjBean {
    @Test
    void testObj() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("obj-bean.xml");
        Student stu1 = context.getBean("stu1", Student.class);
        System.out.println("stu1 = " + stu1);
    }
    //    @Test
//    void testObj2() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("obj-bean.xml");
//        Student stu4 = context.getBean("stu2", Student.class);
//        System.out.println("stu2 = " + stu2);
//    }
    @Test
    void testObj3() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("obj-bean.xml");
        Student stu3 = context.getBean("stu3", Student.class);
        System.out.println("stu3 = " + stu3);
    }
    @Test
    void testObj4() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("obj-bean.xml");
        Student stu4 = context.getBean("stu4", Student.class);
        System.out.println("stu4 = " + stu4);
    }
}
