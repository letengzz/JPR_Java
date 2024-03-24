package com.hjc.demo.dao.impl;

import com.hjc.demo.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }
}