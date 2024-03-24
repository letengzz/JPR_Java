package com.hjc.demo;

import com.hjc.demo.config.SpringConfig;
import com.hjc.demo.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class TxByAnnotationTest {
    // TODO 使用beans.xml
//    @Autowired
//    private BookController bookController;
//
//    @Test
//    void testBuyBook() {
//        bookController.buyBook(1,1);
//    }
    // TODO 使用全注解
    @Test
    public void testTxAllAnnotation(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookController accountService = applicationContext.getBean("bookController", BookController.class);
        accountService.buyBook(1, 1);
    }
}
