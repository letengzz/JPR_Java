package com.hjc.demo;

import com.hjc.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testDelete() {
        //测试删除
        int delete = userMapper.deleteById(2L);
        System.out.println("delete = " + delete);
    }

    @Test
    void testSelect() {
        //测试查询
        userMapper.selectList(null).forEach(System.out::println);
    }
}