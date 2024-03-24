package com.hjc.demo.service.impl;

import com.hjc.demo.annotation.Bean;
import com.hjc.demo.annotation.Di;
import com.hjc.demo.dao.UserDao;
import com.hjc.demo.service.UserService;
/**
 * @author hjc
 */
@Bean
public class UserServiceImpl implements UserService {
    @Di
    private UserDao userDao;

    @Override
    public void out() {
        userDao.print();
        System.out.println("Service层执行结束");
    }
}
