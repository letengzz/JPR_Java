package com.hjc.demo;

import com.hjc.demo.bean.HelloWorld;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {
    private Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

    /**
     * 根据id获取bean
     */
    @Test
    void testGetBean() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        HelloWorld helloworld = (HelloWorld) ac.getBean("helloWorld");
        helloworld.sayHello();
        logger.info("执行成功");
    }

    /**
     * 根据类型获取bean
     */
    @Test
    void testType() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        HelloWorld helloworld = ac.getBean(HelloWorld.class);
        helloworld.sayHello();
        logger.info("执行成功");
    }

    /**
     * 根据id和类型获取bean
     */
    @Test
    void testIdAndType() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        HelloWorld helloworld = ac.getBean("helloWorld",HelloWorld.class);
        helloworld.sayHello();
        logger.info("执行成功");
    }
}
