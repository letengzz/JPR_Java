package com.hjc.demo;

import com.hjc.robot.anntation.EnableRobot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hjc
 */
@SpringBootApplication
@EnableRobot
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
