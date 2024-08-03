package com.hjc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hjc
 */
@SpringBootApplication
@MapperScan("com.hjc.demo.mapper")
public class BasicProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicProgramApplication.class, args);
    }

}
