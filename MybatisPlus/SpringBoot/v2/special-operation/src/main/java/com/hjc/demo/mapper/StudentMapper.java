package com.hjc.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjc.demo.pojo.Student;
import com.hjc.demo.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * mapper接口继承BaseMapper 包含了基本的CRUD操作
 * IDEA在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确的执行。
 * 为了避免报错，可以在mapper接口上添加 @Repository 注解
 * @author hjc
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
}