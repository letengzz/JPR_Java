package com.hjc.demo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjc.demo.pojo.User;
import com.hjc.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    /**
     * 插入操作
     */
    @Test
    void testSave() {
        //插入一条数据
        User user = new User();
        user.setName("李贝");
        user.setEmail("2331@qq.com");
        user.setAge(15);
        boolean result = userService.save(user);
        System.out.println("插入一条信息: " + result);

        //批量插入数据
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user1 = new User();
            user1.setName("测试" + i);
            user1.setAge(10 + i);
            user1.setEmail("test" + i + "@163.com");
            users.add(user1);
        }
        boolean saveBatch = userService.saveBatch(users);
        System.out.println("批量插入数据: " + saveBatch);

        //批量插入数据(按照插入批次数量)
        ArrayList<User> users1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user1 = new User();
            user1.setName("测试" + i);
            user1.setAge(10 + i);
            user1.setEmail("test" + i + "@163.com");
            users1.add(user1);
        }
        boolean saveBatch1 = userService.saveBatch(users1, 2);
        System.out.println("批量插入数据(按照插入批次数量): " + saveBatch1);

        //saveOrUpdateBatch既有修改功能又有修改功能
        //区分：看实体类有没有id 有id是修改 没有id是添加
        User user1 = new User();
        user1.setName("李贝");
        user1.setEmail("2331@qq.com");
        user1.setAge(15);
        boolean saveOrUpdate = userService.saveOrUpdate(user1);
        System.out.println(saveOrUpdate);

        //规则同上 执行批量添加或更改
        ArrayList<User> users2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user2 = new User();
            user2.setId(Long.getLong(UUID.randomUUID().toString()));
            user2.setName("测试" + i);
            user2.setAge(10 + i);
            user2.setEmail("test" + i + "@163.com");
            users2.add(user1);
        }
        boolean saveOrUpdateBatch = userService.saveOrUpdateBatch(users2);
        System.out.println(saveOrUpdateBatch);
    }

    /**
     * 批量删除
     */
    @Test
    void testRemove() {
        //根据id删除
        User user = userService.getById(1L);
        userService.removeById(user);
        userService.removeById(2L);
        //是否启用填充(为true的情况,会将入参转换实体进行delete删除)
        userService.removeById(3L,true);

        //根据多个id删除
        userService.removeByIds(Arrays.asList(1L,2L));
        //是否填充(为true的情况,会将入参转换实体进行delete删除)
        userService.removeByIds(Arrays.asList(3L,4L),true);

        //批量删除
        userService.removeBatchByIds(Arrays.asList(3L,4L));
        //是否填充(为true的情况,会将入参转换实体进行delete删除)
        userService.removeBatchByIds(Arrays.asList(3L,4L),true);
        //批量删除(按照插入批次数量)
        userService.removeBatchByIds(Arrays.asList(3L,4L),1);
        //是否填充(为true的情况,会将入参转换实体进行delete删除)(按照插入批次数量)
        userService.removeBatchByIds(Arrays.asList(3L,4L),1,true);

        //使用map进行删除
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",1);
        userService.removeByMap(map);
    }
    /**
     * 测试修改
     */
    @Test
    void testUpdate() {
        //根据id修改
        userService.updateById(new User(1L,"孙策",10,"222"));
        //批量修改
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(2L,"孙策",12,"123"));
        list.add(new User(3L,"孙权",44,"456"));
        userService.updateBatchById(list);
        //批量插入数据(按照插入批次数量)
        userService.updateBatchById(list,2);
    }

    /**
     * 测试查询
     */
    @Test
    void testSelect() {
        //查询总记录数
        long count = userService.count();
        System.out.println("count = " + count);

        //查询所有数据
        userService.list().forEach(System.out::println);


        //查询所有数据 格式：List<Map<String, Object>>
        List<Map<String, Object>> maps = userService.listMaps();
        System.out.println(maps);

        //根据id批量查询
        userService.listByIds(Arrays.asList(2L,3L)).forEach(System.out::println);

        //查询所有主键id
        //说明：取出的数据并不包括对象所有的字段，最多只能返回一个字段
        userService.listObjs().forEach(System.out::println);

        //使用map查询数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","李贝");
        userService.listByMap(map).forEach(System.out::println);

        //根据id查询数据
        User user = userService.getById(1L);
        System.out.println("user = " + user);

        //使用mapper 查询所有数据
        BaseMapper<User> baseMapper = userService.getBaseMapper();
        baseMapper.selectList(null).forEach(System.out::println);
    }
}
