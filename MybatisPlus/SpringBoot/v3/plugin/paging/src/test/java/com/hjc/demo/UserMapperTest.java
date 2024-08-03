package com.hjc.demo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //自带插件需要开启配置
    @Test
    void testSelectList() {
        Page<User> page = new Page<>(1,3);
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectPage(page, null);
        //当前页的数据
        page.getRecords().forEach(user ->System.out.println("当前页的数据: "+user));
        //获取当前页的页码
        System.out.println("获取当前页的页码: "+page.getCurrent());
        //获取每页的条数
        System.out.println("获取每页的条数: "+page.getSize());
        //获取总页数
        System.out.println("获取总页数: "+page.getTotal());
        //是否存在上一页
        System.out.println("是否存在上一页: "+page.hasPrevious());
        //是否存在下一页
        System.out.println("是否存在下一页: "+page.hasNext());
        //当前分页总页数
        System.out.println("当前分页总页数: "+page.getPages());
    }

    @Test
    void testSelectList2() {
        Page<User> page = new Page<>(1,3);
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectPageVo(page,10);
        //当前页的数据
        page.getRecords().forEach(user ->System.out.println("当前页的数据: "+user));
        //获取当前页的页码
        System.out.println("获取当前页的页码: "+page.getCurrent());
        //获取每页的条数
        System.out.println("获取每页的条数: "+page.getSize());
        //获取总页数
        System.out.println("获取总页数: "+page.getTotal());
        //是否存在上一页
        System.out.println("是否存在上一页: "+page.hasPrevious());
        //是否存在下一页
        System.out.println("是否存在下一页: "+page.hasNext());
        //当前分页总页数
        System.out.println("当前分页总页数: "+page.getPages());
    }
}