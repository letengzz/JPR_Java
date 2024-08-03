package com.hjc.demo.controller;

import com.hjc.demo.entity.SysUser;
import com.hjc.demo.enume.UserStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author hjc
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
    @GetMapping("/getUser")
    public SysUser getUser(){
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUsername("admin");
        sysUser.setNickname("管理员");
        sysUser.setStatus(UserStatusEnum.ENABLE);
        return sysUser;
    }


    @PostMapping("/saveUser")
    private SysUser saveUser(@RequestBody SysUser userJson){
        System.out.println("保存的用户：" + userJson);
        System.out.println("用户状态：" + userJson.getStatus());
        return userJson;
    }
}
