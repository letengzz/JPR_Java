package com.hjc.demo.config;

import com.hjc.demo.entity.Pet;
import com.hjc.demo.entity.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootConfiguration
@ConditionalOnBean(value = User.class)  //放在类级别上，如果注解判断生效，则整个配置类才生效
public class MyConfig2 {
    @Bean("tom")
    @ConditionalOnClass(name = "com.hjc.demo.config.JdbcConfig")  //放在方法级别，单独对这个方法进行注解判断
    public Pet tomPet(){
        Pet tom = new Pet();
        tom.setName("Tom");
        return tom;
    }

    @Bean("jerry")
    @ConditionalOnMissingClass(value = "com.hjc.demo.config.JdbcConfig")
    public Pet JerryPet(){
        Pet jerry = new Pet();
        jerry.setName("Jerry");
        return jerry;
    }
}
