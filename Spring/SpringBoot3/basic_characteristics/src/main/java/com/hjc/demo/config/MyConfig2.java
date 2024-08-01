package com.hjc.demo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@PropertySource("classpath:abc.yaml")
public class MyConfig2 {
}
