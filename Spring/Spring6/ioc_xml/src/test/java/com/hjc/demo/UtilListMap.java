package com.hjc.demo;

import com.hjc.demo.special.Expression;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UtilListMap {
    @Test
    void testUtilListMap() {
        ApplicationContext context = new ClassPathXmlApplicationContext("util-list-map.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println("stu = " + stu);
    }
}
