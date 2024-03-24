package com.hjc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.demo.entity.User;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 * @author hjc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
