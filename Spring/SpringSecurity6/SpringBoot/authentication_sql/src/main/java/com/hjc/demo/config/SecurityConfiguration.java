package com.hjc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource,
//                                                 PasswordEncoder encoder) {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        //仅首次启动时创建一个新的用户用于测试，后续无需创建
//        manager.createUser(User.withUsername("user")
//                .password(encoder.encode("password")).roles("USER").build());
//        return manager;
//    }



    //手动创建一个AuthenticationManager用于处理密码校验
    private AuthenticationManager authenticationManager(UserDetailsManager manager,
                                                        PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(manager);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource,
                                                 PasswordEncoder encoder) throws Exception {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        //为UserDetailsManager设置AuthenticationManager即可开启重置密码的时的校验
        manager.setAuthenticationManager(authenticationManager(manager, encoder));
        return manager;
    }
}