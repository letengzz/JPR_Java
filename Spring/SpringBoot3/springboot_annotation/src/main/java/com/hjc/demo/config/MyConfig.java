package com.hjc.demo.config;

import com.hjc.demo.entity.Pet;
import com.hjc.demo.entity.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.*;

/**
 * 配置类
 * @author hjc
 */
@Configuration(proxyBeanMethods = true)  //这是一个配置类，替代了以前的配置文件，配置类本身也是容器中的组件
//@SpringBootConfiguration
@Import(JdbcConfig.class)
@ImportResource("classpath:beans.xml")
public class MyConfig {

    /**
     * 组件默认是单实例的
     * @return
     */
    @Bean("user")  //替代以前的Bean标签，组件在容器中的名字默认就是方法名，可以直接修改注解的值
    public User user(){
        User zhangsan = new User();
        zhangsan.setName("张三");
        zhangsan.setAge(23);
        zhangsan.setPet(tomPet());
        return zhangsan;
    }

    @Bean
    @Scope("singleton")
    public Pet tomPet(){
        Pet tom = new Pet();
        tom.setName("Tom");
        return tom;
    }
}
