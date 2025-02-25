package com.hjc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hjc.demo.mapper")
@SpringBootApplication
public class SaTokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaTokenDemoApplication.class, args);
    }

}
