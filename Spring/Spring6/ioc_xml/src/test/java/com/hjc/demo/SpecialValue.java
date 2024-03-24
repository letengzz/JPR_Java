package com.hjc.demo;

import com.hjc.demo.special.Expression;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpecialValue {
    private Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

    @Test
    void testSpecialValue() {
        ApplicationContext context = new ClassPathXmlApplicationContext("special-value.xml");
        Expression expression = context.getBean("expression", Expression.class);
        System.out.println("expression = " + expression);
    }
}
