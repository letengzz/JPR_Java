package com.hjc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity   //开启WebSecurity相关功能
public class SecurityConfiguration {
    @Bean   //UserDetailsService就是获取用户信息的服务
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        //每一个UserDetails就代表一个用户信息，其中包含用户的用户名和密码以及角色
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")  //角色目前我们不需要关心，随便写就行，后面会专门讲解
//                .build();
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("password"))   //这里将密码进行加密后存储
                .roles("USER")  //角色目前我们不需要关心，随便写就行，后面会专门讲解
                .build();
        System.out.println(encoder.encode("password"));  //一会观察一下加密出来之后的密码长啥样

//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN", "USER")
//                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("password"))   //这里将密码进行加密后存储
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
        //创建一个基于内存的用户信息管理器作为UserDetailsService
    }

    //这里将BCryptPasswordEncoder直接注册为Bean，Security会自动进行选择
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}