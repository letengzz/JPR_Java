package com.hjc.demo.config;

import com.hjc.demo.entity.Admin;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author hjc
 */
@Configuration
public class ApiConfig {
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("hjc-user")
                .pathsToMatch("/user/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("hjc-admin")
                .pathsToMatch("/admin/**")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(PostMapping.class))
                .build();
    }
}
