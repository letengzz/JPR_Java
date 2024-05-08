package com.hjc.demo.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    //单例 没有线程安全问题 只用配置一个bean即可
    @Bean
    public MinioClient minioClient(){
        //通过构造器 使用链式编程 构建客户端对象
        return MinioClient.builder()
                .endpoint("http://localhost:9000")  //构建一个端点
                .credentials("admin","password")  //访问的账号和密码
                .build();
    }
}
