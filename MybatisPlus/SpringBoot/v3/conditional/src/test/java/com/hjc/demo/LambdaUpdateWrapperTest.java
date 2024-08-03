package com.hjc.demo;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LambdaUpdateWrapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    void testUpdateWrapper() {
        //将用户名中包含有a并且 年龄大于20或邮箱为null的用户信息修改
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName,"a")
                .and(i ->i.ge(User::getAge,20).or().isNull(User::getEmail));
        wrapper.set(User::getName,"小王").set(User::getEmail,"4566");
        int update = userMapper.update(null, wrapper);
        System.out.println("update = " + update);
    }
}
