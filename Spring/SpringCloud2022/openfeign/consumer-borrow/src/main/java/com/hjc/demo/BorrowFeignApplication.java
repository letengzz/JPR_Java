package com.hjc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Borrow 启动类
 * @author hjc
 */
@SpringBootApplication
@EnableFeignClients  //开启OpenFeign
public class BorrowFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorrowFeignApplication.class, args);
    }
}
