package com.hjc.demo.client;

import com.hjc.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hjc
 */
@FeignClient(value = "user-service",path = "/user")
public interface UserClient {

    @RequestMapping("/{uid}")
    User getUserById(@PathVariable("uid") int uid);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);
}