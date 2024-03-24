package com.hjc.demo.dao.impl;

import com.hjc.demo.annotation.Bean;
import com.hjc.demo.dao.UserDao;

/**
 * @author hjc
 */
@Bean
public class UserDaoImpl implements UserDao {

    @Override
    public void print() {
        System.out.println("Dao层执行结束");
    }
}
