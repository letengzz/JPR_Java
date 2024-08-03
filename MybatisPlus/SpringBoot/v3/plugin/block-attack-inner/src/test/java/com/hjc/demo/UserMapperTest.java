package com.hjc.demo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import com.hjc.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserService userService;

    @Test
    public void testQuick8(){
        User user = new User();
        user.setName("custom_name");
        user.setEmail("xxx@mail.com");
        //Caused by: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        //全局更新,报错
        userService.saveOrUpdate(user,null);
    }
}