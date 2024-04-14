package com.hjc.demo.client;

import com.hjc.demo.client.factory.UserFallbackFactory;
import com.hjc.demo.client.fallback.UserFallbackClient;
import com.hjc.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hjc
 */
//@FeignClient(value = "user-service",path = "/user")
//fallback参数指定为刚刚编写的实现类
@FeignClient(value = "user-service",path = "/user"
//        ,fallback = UserFallbackClient.class //声明为user-service服务的HTTP请求客户端
        ,fallbackFactory = UserFallbackFactory.class
)
public interface UserClient {

    @RequestMapping("/{uid}")
    User getUserById(@PathVariable("uid") Integer uid);
}