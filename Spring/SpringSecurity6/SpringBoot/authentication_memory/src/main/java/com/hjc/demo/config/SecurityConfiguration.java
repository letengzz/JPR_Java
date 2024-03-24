package com.hjc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author hjc
 */
@Configuration
//@EnableWebSecurity //SpringBoot项目中可不添加该注解
public class SecurityConfiguration {

    @Bean   //UserDetailsService就是获取用户信息的服务
    public UserDetailsService userDetailsService() {

        //创建一个基于内存的用户信息管理器作为UserDetailsService
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //每一个UserDetails就代表一个用户信息，其中包含用户的用户名和密码以及角色
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user") //自定义用户名
                .password("password") //自定义密码
                .roles("USER")  //自定义角色
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN", "USER")
                .build();
        manager.createUser(user);
        manager.createUser(admin);
        return manager;
    }

}