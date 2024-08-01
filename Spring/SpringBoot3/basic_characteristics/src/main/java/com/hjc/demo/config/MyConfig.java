package com.hjc.demo.config;

import com.hjc.demo.bean.Pig;
import com.hjc.demo.bean.Sheep;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author hjc
 */
@SpringBootConfiguration
@Profile("prod")  //只有指定环境被激活整个类的所有配置才能生效
public class MyConfig {
    @Bean
    @Profile("prod")
    public Sheep sheep(){
        return new Sheep();
    }
    @Bean
    @Profile({"dev","test"})
    public Pig pig(){
        return new Pig();
    }

}
