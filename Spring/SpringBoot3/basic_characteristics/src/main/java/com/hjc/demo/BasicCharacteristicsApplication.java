package com.hjc.demo;

import com.hjc.demo.bean.Pig;
import com.hjc.demo.bean.Sheep;
import com.hjc.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hjc
 */
@SpringBootApplication //主程序类
@Slf4j
public class BasicCharacteristicsApplication {

    public static void main(String[] args) {
        //SpringApplication：Boot应用的核心API入口
//        SpringApplication.run(BasicCharacteristicsApplication.class, args);
        //1. 自定义SpringApplication的底层设置
//        SpringApplication application = new SpringApplication(BasicCharacteristicsApplication.class);
//        //todo 调整SpringApplication的参数
////        application.setDefaultProperties();
//        //todo 配置文件优先级高于程序化调整的优先级
//        application.setBannerMode(Banner.Mode.CONSOLE);
//        //2.运行SpringApplication
//        application.run(args);

        //Builder方式构建SpringApplication：通过FluentAPI进行设置
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .main(BasicCharacteristicsApplication.class)
                .sources(BasicCharacteristicsApplication.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .properties("server.port=9999","aaa=bbb") //springboot所有配置项都可以在这里定义
                .run(args);
        Person p = context.getBean(Person.class);
        System.out.println("p = " + p);

        try {
            Sheep sheep = context.getBean(Sheep.class);
            log.info("sheep:{}",sheep);
        } catch (BeansException e) {

        }
        try {
            Pig pig = context.getBean(Pig.class);
            log.info("pig:{}",pig);
        } catch (BeansException e) {
        }


//        SpringApplication app = new SpringApplication(BasicCharacteristicsApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.setLogStartupInfo(false);
//        app.setRegisterShutdownHook(false);
//        Map<String, Object> pro = new HashMap<>();
//        pro.put("server.port", "9999");
//        app.setDefaultProperties(pro);
//        app.run(args);

    }
}
