package com.hjc.demo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testPageHelper() {
        PageInfo<User> page = PageHelper.startPage(1,2).doSelectPageInfo(()->{
            userMapper.selectList(null);
        });
        page.getList().forEach(System.out :: println);
        System.out.println("总行数=" + page.getTotal());
        System.out.println("当前页=" + page.getPageNum());
        System.out.println("每页行数=" + page.getPageSize());
        System.out.println("总页数=" + page.getPages());
        System.out.println("起始行数=" + page.getStartRow());
        System.out.println("是第一页=" + page.isIsFirstPage());
        System.out.println("是最后页=" + page.isIsLastPage());
        System.out.println("还有下一页=" + page.isHasNextPage());
        System.out.println("还有上一页=" + page.isHasPreviousPage());
        System.out.println("页码列表=" + Arrays.toString(page.getNavigatepageNums()));
    }
}
