package com.hjc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceTest {
    @Test
    void testResource() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("bean.xml");
        ResourceBean resourceBean = ctx.getBean("resourceBean",ResourceBean.class);
        resourceBean.parse();
    }

    @Test
    void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:bean*.xml");
        User bean = ctx.getBean("user",User.class);
        System.out.println("bean = " + bean);
    }
}
