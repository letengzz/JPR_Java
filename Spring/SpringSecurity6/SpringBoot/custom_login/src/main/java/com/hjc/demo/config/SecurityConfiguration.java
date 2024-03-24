package com.hjc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author hjc
 */
@Configuration
@EnableWebSecurity   //开启WebSecurity相关功能
public class SecurityConfiguration {
    //这里将BCryptPasswordEncoder直接注册为Bean，Security会自动进行选择
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,PersistentTokenRepository tokenRepository) throws Exception {
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/static/**").permitAll();   //将所有的静态资源放行，一定要添加在全部请求拦截之前
                    auth.anyRequest().authenticated();    //将所有请求全部拦截，一律需要验证
                })
//                .authorizeHttpRequests(authorize ->
//                        authorize.anyRequest() //对所有请求开启授权保护
//                                .authenticated()) //已认证的请求会被自动授权
                //以下是表单登录相关配置
                .formLogin(conf -> {
                    conf.loginPage("/login"); //将登录页设置为我们自己的登录页面
                    conf.failureUrl("/login?error"); //登录失败的返回地址
                    conf.loginProcessingUrl("/doLogin"); //登录表单提交的地址，可以自定义
                    conf.defaultSuccessUrl("/");   //登录成功后跳转的页面
                    conf.permitAll();    //将登录相关的地址放行，否则未登录的用户连登录界面都进不去
                    //用户名和密码的表单字段名称，不过默认就是这个，可以不配置，除非有特殊需求
                    conf.usernameParameter("username");
                    conf.passwordParameter("password");
                })
                //以下是退出登录相关配置
                .logout(conf -> {
                    conf.logoutUrl("/doLogout");   //退出登录地址，跟上面一样可自定义
                    conf.logoutSuccessUrl("/login");  //退出登录成功后跳转的地址，这里设置为登录界面
                    conf.permitAll();
                })
                .rememberMe(conf -> {
                    conf.alwaysRemember(false);  //这里不要开启始终记住，我们需要配置为用户自行勾选
//                    conf.rememberMeParameter("remember-me");   //记住我表单字段，默认就是这个，可以不配置
//                    conf.rememberMeCookieName("xxxx");  //记住我设置的Cookie名字，也可以自定义，不过没必要
                    conf.rememberMeParameter("remember-me");
                    conf.tokenRepository(tokenRepository);      //设置刚刚的记住我持久化存储库
                    conf.tokenValiditySeconds(3600 * 7);   //设置记住我有效时间为7天
                })
                .csrf(AbstractHttpConfigurer::disable) //关闭csrf攻击防御
                .build();
    }
    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        //在启动时自动在数据库中创建存储记住我信息的表，仅第一次需要，后续不需要
        repository.setCreateTableOnStartup(true);
        repository.setDataSource(dataSource);
        return repository;
    }
}