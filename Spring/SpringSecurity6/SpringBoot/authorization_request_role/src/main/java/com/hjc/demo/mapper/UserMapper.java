package com.hjc.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjc.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hjc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}