package com.hjc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 * SpringBootApplication等同于EnableAutoConfiguration+SpringBootConfiguration+ComponentScan("com.hjc.demo")
 * scanBasePackages扫描指定包
 * 主程序所在包及其下面的所有子包里面的组件都会被默认扫描进来
 * @author hjc
 */
@SpringBootApplication(scanBasePackages = "com.hjc.demo")
public class MainApplication {

    public static void main(String[] args) {
        //返回IoC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //查看容器中的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name :
                names) {
            System.out.println("name = " + name);
        }

    }
}