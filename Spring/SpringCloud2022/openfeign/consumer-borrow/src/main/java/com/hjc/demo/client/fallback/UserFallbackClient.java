package com.hjc.demo.client.fallback;

import com.hjc.demo.client.UserClient;
import com.hjc.demo.entity.User;
import org.springframework.stereotype.Component;

@Component   //注意，需要将其注册为Bean，Feign才能自动注入
public class UserFallbackClient implements UserClient {
    @Override
    public User getUserById(Integer uid) {   //自行对其进行实现，并返回替代方案
        User user = new User();
        user.setName("熔断降级方法返回");
        return user;
    }
}