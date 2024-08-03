package com.hjc.demo.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hjc.demo.enume.UserStatusEnum;
import com.hjc.demo.serializer.UserStatusEnumSerializer;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    //定义转换规则
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            //Serializer：后端写给前端：枚举变为code
            //Deserializer：前端写给后端：code变为枚举
            builder.serializerByType(UserStatusEnum.class, new UserStatusEnumSerializer());
        };
    }
}
