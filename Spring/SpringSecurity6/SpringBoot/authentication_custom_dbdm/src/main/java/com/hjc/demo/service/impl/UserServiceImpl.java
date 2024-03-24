package com.hjc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.demo.config.DBUserDetailsManager;
import com.hjc.demo.entity.User;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 * @author hjc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(String username, String password) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(username) //自定义用户名
                .password(password) //自定义密码
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
