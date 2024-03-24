package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListBean {

    @Test
    void testListBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("list-bean.xml");
        Clazz clazz = context.getBean("clazz", Clazz.class);
        System.out.println("clazz = " + clazz);
    }
}
