package com.hjc.demo;

import com.hjc.demo.service.ProductService;
import com.hjc.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Test
    public void testDynamicDataSource(){
        System.out.println(userService.getById(1L));
        System.out.println(productService.getById(1L));
    }
}