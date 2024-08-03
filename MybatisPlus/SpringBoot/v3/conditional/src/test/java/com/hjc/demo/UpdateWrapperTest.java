package com.hjc.demo;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateWrapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    void testUpdateWrapper() {
        //将用户名中包含有a并且 年龄大于20或邮箱为null的用户信息修改
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.like("name","a")
                .and(i ->i.ge("age",20).or().isNull("email"));
        wrapper.set("name","小王").set("email","4566");
        int update = userMapper.update(null, wrapper);
        System.out.println("update = " + update);
    }
}
