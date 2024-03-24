package com.hjc.demo;

import com.hjc.demo.bean.HelloWorld;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {
    private Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);
    @Test
    void testHelloWorld() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        HelloWorld helloworld = (HelloWorld) ac.getBean("helloWorld");
        helloworld.sayHello();
        logger.info("执行成功");
    }
}
