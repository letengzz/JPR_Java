package com.hjc.demo.config;

import com.alibaba.fastjson2.JSON;
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

import java.util.HashMap;

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
    public SecurityFilterChain filterChain(HttpSecurity http, PersistentTokenRepository tokenRepository) throws Exception {
        //authorizeRequests()：开启授权保护
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/static/**").permitAll();   //将所有的静态资源放行，一定要添加在全部请求拦截之前
            //具有USER_LIST权限的用户可以访问/user/list
            auth.requestMatchers("/user/list").hasAuthority("USER_LIST");
            //具有USER_ADD权限的用户可以访问/user/index
//            auth.requestMatchers("/user/index").hasAuthority("USER_INDEX");
            auth.requestMatchers("/user/index").hasAnyAuthority("page:index");

            //anyRequest()：对所有请求开启授权保护
            //authenticated()：已认证请求会自动被授权
            auth.anyRequest().authenticated();    //将所有请求全部拦截，一律需要验证
        });
        //登录相关配置
        http.formLogin(conf -> {
            conf.loginPage("/login"); //将登录页设置为我们自己的登录页面
            conf.failureUrl("/login?error"); //登录失败的返回地址
            conf.loginProcessingUrl("/doLogin"); //登录表单提交的地址，可以自定义
            conf.defaultSuccessUrl("/");   //登录成功后跳转的页面
            conf.permitAll();    //将登录相关的地址放行，否则未登录的用户连登录界面都进不去
            //认证成功时的处理
            conf.successHandler(new MyAuthenticationSuccessHandler());
            //认证失败时的处理
            conf.failureHandler(new MyAuthenticationFailureHandler());
        });
        //退出登录相关配置
        http.logout(conf -> {
            //注销成功时的处理
            conf.logoutSuccessHandler(new MyLogoutSuccessHandler());
        });
        //记住我
        http.rememberMe(conf -> {
            conf.alwaysRemember(false);  //这里不要开启始终记住，我们需要配置为用户自行勾选
//                    conf.rememberMeParameter("remember-me");   //记住我表单字段，默认就是这个，可以不配置
//                    conf.rememberMeCookieName("xxxx");  //记住我设置的Cookie名字，也可以自定义，不过没必要
            conf.rememberMeParameter("remember-me");
            conf.tokenRepository(tokenRepository);      //设置刚刚的记住我持久化存储库
            conf.tokenValiditySeconds(3600 * 7);   //设置记住我有效时间为7天
        });
        //关闭csrf攻击防御
        http.csrf(AbstractHttpConfigurer::disable);
        //错误处理
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证的接口
        });
        //跨域
        http.cors(withDefaults());

        //会话管理
        http.sessionManagement(session -> {
            session
                    .maximumSessions(1) //设置会话的最大数量为1，即同一账号只能同时登录一个设备
                    .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });
        //错误处理
        http.exceptionHandling(exception  -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证的接口
            exception.accessDeniedHandler((request, response, e)->{ //请求未授权的接口

                //创建结果对象
                HashMap result = new HashMap();
                result.put("code", -1);
                result.put("message", "没有权限");

                //转换成json字符串
                String json = JSON.toJSONString(result);

                //返回响应
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
            });
        });
        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        //在启动时自动在数据库中创建存储记住我信息的表，仅第一次需要，后续不需要
//        repository.setCreateTableOnStartup(true);
        repository.setDataSource(dataSource);
        return repository;
    }
}