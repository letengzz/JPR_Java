package com.hjc;

import org.junit.jupiter.api.Test;

/**
 * @author hjc
 */
public class LombokTest {
    /**
     *  链式调用测试
     */
    @Test
    void chainTest() {
        User user = new User().setName("张三").setAge(15);
        System.out.println("user = " + user);
    }

    /**
     *  建造者测试
     */
    @Test
    void builderTest(){
        User user = User.builder().name("李四").age(18).build();
        System.out.println("user = " + user);
    }
}
