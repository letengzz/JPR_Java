package com.hjc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author hjc
 */
@Configuration
public class SpringConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

