package com.hjc.demo.client.factory;

import com.hjc.demo.client.fallback.UserFallbackClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackFactory implements FallbackFactory<UserFallbackClient> {
    @Override
    public UserFallbackClient create(Throwable cause) {
        System.out.println("UserFallbackFactory");
        cause.printStackTrace();
        return new UserFallbackClient();
    }
}
