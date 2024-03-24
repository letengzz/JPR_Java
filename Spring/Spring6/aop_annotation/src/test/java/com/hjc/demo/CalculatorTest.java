package com.hjc.demo;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculatorTest {
    private Logger logger = LoggerFactory.getLogger(Calculator.class);

    @Test
    void testAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Calculator calculator = context.getBean(Calculator.class);
        int add = calculator.add(1, 1);
        logger.info("执行成功:" + add);
    }
}
