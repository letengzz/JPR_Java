package com.hjc.demo.service.impl;

import com.hjc.demo.dao.UserDao;
import com.hjc.demo.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }

}