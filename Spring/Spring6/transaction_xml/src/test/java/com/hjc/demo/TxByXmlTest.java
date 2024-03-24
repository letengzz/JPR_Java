package com.hjc.demo;

import com.hjc.demo.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class TxByXmlTest {
    @Autowired
    private BookController bookController;

    @Test
    void testBuyBook() {
        bookController.buyBook(1,1);
    }
}
