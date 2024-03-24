package com.hjc.demo.mapper;

import com.hjc.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper映射
 * @author hjc
 */
public interface UserMapper {
    @Select("select * from user")
    List<User> selectAllUser();

    User selectUserById(@Param("id") Integer id);
}