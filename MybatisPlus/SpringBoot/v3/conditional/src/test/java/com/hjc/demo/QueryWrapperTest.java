package com.hjc.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class QueryWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
    }

    @Test
    public void test05() {
        //查询用户信息的username和age字段
        //SELECT name,age FROM t_user
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 组装删除条件
     */
    @Test
    void testDelete() {
        //删除邮箱地址为null的用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNull("email");
        int delete = userMapper.delete(wrapper);
        System.out.println("delete = " + delete);
    }

    /**
     * 组装修改条件
     */
    @Test
    void testUpdate() {
        //将年龄大于20并且用户名中包含有a 或 邮箱为null的用户信息修改
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20)
                .like("name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小白");
        user.setEmail("232332");
        int result = userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }

    /**
     * 条件的优先级
     */
    @Test
    void test() {
        //将年龄大于20并且用户名中包含有a 或 邮箱为null的用户信息修改
        //lambda中的条件优先执行
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setName("小白");
        user.setEmail("232332");
        int result = userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }

    /**
     * 组装排序条件
     */
    @Test
    void testSort() {
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age")
                .orderByAsc("id");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 组装查询条件
     */
    @Test
    void testSelect() {
        // 查询用户名包含a 年龄在20到30之间 邮箱信息不为null的信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 组装select子句
     */
    @Test
    void testSelect1() {
        //查询用户的用户名、年龄、邮箱信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name","age","email");
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    /**
     * 组装子查询
     */
    @Test
    void testSubQuery() {
        //查询id小于等于100的用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from t_user where id <= 100");
        userMapper.selectList(wrapper).forEach(System.out::println);

    }

    /**
     * 组装条件
     */
    @Test
    void testCondition() {
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        //不使用condition组装条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            //isNotBlank判断某个字符串是否不为空字符串，不为null，不为空白符
            wrapper.like("name",username);
        }
        if (ageBegin != null){
            wrapper.ge("age",ageBegin);
        }
        if (ageEnd != null){
            wrapper.le("age",ageEnd);
        }
        userMapper.selectList(wrapper).forEach(System.out::println);

        //使用condition组装条件
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        wrapper1.like(StringUtils.isNotBlank(username),"name",username)
                .ge(ageBegin != null,"age",ageBegin)
                .le(ageEnd != null,"age",ageEnd);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}