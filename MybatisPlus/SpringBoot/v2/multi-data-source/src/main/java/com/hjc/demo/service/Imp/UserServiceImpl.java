package com.hjc.demo.service.Imp;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import com.hjc.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl实现了IService，提供了IService中基础功能的实现
 * 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现
 * @author hjc
 */
@DS("master")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
