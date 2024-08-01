package com.hjc.demo.controller;

import com.hjc.demo.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * @author hjc
 */
@RestController
@RequestMapping("user")
@Tag(name = "用户", description = "用户接口")
public class UserController {
    @Operation(summary = "获取用户信息", description = "获取用户信息的方法")
    @GetMapping("/list")
    public User list(@RequestParam("name")
                     @Parameter(description = "姓名") String name) {
        User user = new User();
        user.setId(1);
        user.setName(name);
        user.setAddress("中国");
        return user;
    }
}
