package com.hjc.demo;

import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 新增功能
     * MyBatis-Plus在实现插入数据时，会默认基于雪花算法的策略生成id
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail( "zhangsan@qq.com");
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userMapper.insert(user);
        System.out.println("受影响行数："+result);
        //id自动获取：1629070359040888833
        System.out.println("id自动获取："+user.getId());
    }

    /**
     * 删除功能
     */
    @Test
    void testDelete() {
        //通过id删除用户信息
        int result = userMapper.deleteById(1L);
        System.out.println("删除id为1的用户: " + result);

        //根据map集合中所设置的条件删除用户信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        int result2 = userMapper.deleteByMap(map);
        System.out.println("删除名字为张三，age为23岁的用户: " + result2);

    }

    /**
     * 修改功能
     */
    @Test
    void testUpdate() {
        User user = userMapper.selectById(2L);
        user.setAge(45);
        //根据id修改信息
        int result = userMapper.updateById(user);
        System.out.println("根据id修改信息: " + result);
    }

    /**
     * 查询功能
     */
    @Test
    void testSelect() {
        //根据id查询用户
        User user = userMapper.selectById(5L);
        System.out.println("根据id查询用户: " + user);

        //根据map集合中的条件查询用户信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        userMapper.selectByMap(map).forEach(u -> System.out.println("根据map集合中的条件查询用户信息: " + u));
    }

    /**
     * 自定义功能
     */
    @Test
    void testCustomer() {
        //测试自定义
        Map<String, Object> user = userMapper.selectMapById(2L);
        System.out.println("根据id查询为2的用户: "+user);
    }

    /**
     * 批量删除
     */
    @Test
    void testBatchDelete() {
        //通过多个id实现批量删除
        List<Long> list = Arrays.asList(2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("删除id为2,3的用户: " + result);
    }

    /**
     * 批量查询
     */
    @Test
    void testBatchSelect() {
        //根据多个id查询多个用户信息
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        userMapper.selectBatchIds(list).forEach(System.out::println);

        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        //查询所有数据
        userMapper.selectList(null).forEach(System.out::println);
    }
}
