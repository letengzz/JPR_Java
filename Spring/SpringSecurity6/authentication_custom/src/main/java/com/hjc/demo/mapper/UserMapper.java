package com.hjc.demo.mapper;

import com.hjc.demo.pojo.Account;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from account where username = #{username}")
    Account findAccountByName(String username);
}