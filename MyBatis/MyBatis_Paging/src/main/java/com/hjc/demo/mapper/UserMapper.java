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
    List<User> selectAllUserLimit(@Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize);
    @Select("select * from user")
    List<User> selectAllUser();
}