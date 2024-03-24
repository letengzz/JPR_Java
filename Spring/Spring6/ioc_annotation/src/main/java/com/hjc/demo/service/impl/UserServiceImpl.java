package com.hjc.demo.service.impl;

import com.hjc.demo.dao.UserDao;
import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //todo 属性注入：
//    @Autowired
//    private UserDao userDao;

    //todo set注入：
//    private UserDao userDao;
//    @Autowired
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //todo 构造方法注入：
//    private UserDao userDao;
//
//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }
    //todo 形参上注入：
//    private UserDao userDao;
//
//    public UserServiceImpl(@Autowired UserDao userDao) {
//        this.userDao = userDao;
//    }
    //todo 只有一个构造函数，无注解：
//    private UserDao userDao;
//
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }
    //todo Autowired注解和Qualifier注解联合：
//    @Autowired
//    @Qualifier("userDaoImpl") // 指定bean的名字
//    private UserDao userDao;

    //todo Resource注入：根据name注入
//    @Resource(name = "myUserDao")
//    private UserDao userDao;

    //todo Resource注入：根据name注入
//    @Resource
//    private UserDao myUserDao;

    //todo Resource注入：其他情况
//    @Resource
//    private UserDao myUserDao1;

    //todo Resource注入：set注入
    private UserDao userDao;
    @Resource(name = "myUserDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void out() {
        userDao.print();
//        myUserDao.print();
        System.out.println("Service层执行结束");
    }
}
