package com.hjc.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LambdaQueryWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert(){
    }

    /**
     * 组装删除条件
     */
    @Test
    void testDelete() {
        //删除邮箱地址为null的用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNull(User::getEmail);
        int delete = userMapper.delete(wrapper);
        System.out.println("delete = " + delete);
    }

    /**
     * 组装修改条件
     */
    @Test
    void testUpdate() {
        //将年龄大于20并且用户名中包含有a 或 邮箱为null的用户信息修改
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(User::getAge, 20)
                .like(User::getName, "a")
                .or()
                .isNull(User::getEmail);
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
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
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
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(User::getAge)
                .orderByAsc(User::getId);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 组装查询条件
     */
    @Test
    void testSelect() {
        // 查询用户名包含a 年龄在20到30之间 邮箱信息不为null的信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "a")
                .between(User::getAge, 20, 30)
                .isNotNull(User::getEmail);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 组装select子句
     */
    @Test
    void testSelect1() {
        //查询用户的用户名、年龄、邮箱信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getName, User::getAge, User::getEmail);
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    /**
     * 组装子查询
     */
    @Test
    void testSubQuery() {
        //查询id小于等于100的用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.inSql(User::getId, "select id from t_user where id <= 100");
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
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            //isNotBlank判断某个字符串是否不为空字符串，不为null，不为空白符
            wrapper.like(User::getName, username);
        }
        if (ageBegin != null) {
            wrapper.ge(User::getAge, ageBegin);
        }
        if (ageEnd != null) {
            wrapper.le(User::getAge, ageEnd);
        }
        userMapper.selectList(wrapper).forEach(System.out::println);
        //使用condition组装条件
        LambdaQueryWrapper<User> wrapper1 = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
    }
}