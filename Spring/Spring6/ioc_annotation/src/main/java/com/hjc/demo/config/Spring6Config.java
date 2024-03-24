package com.hjc.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan({"com.hjc.demo.controller", "com.hjc.demo.service","com.hjc.demo.dao"})
@ComponentScan("com.hjc.demo")
public class Spring6Config {
}
