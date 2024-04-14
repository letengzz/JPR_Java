package com.hjc.demo.mapper;


import com.hjc.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from db_user where uid = #{uid}")
    User getUserById(Integer uid);
}
