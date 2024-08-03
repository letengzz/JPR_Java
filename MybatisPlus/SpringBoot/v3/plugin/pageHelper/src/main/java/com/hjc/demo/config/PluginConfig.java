package com.hjc.demo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hjc
 */
@Configuration
//扫描mapper接口所在的包
@MapperScan("com.hjc.demo.mapper")
public class PluginConfig {

    //配置pageHelper
    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}