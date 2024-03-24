package com.hjc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.demo.entity.User;

import java.util.List;

/**
 * Service 接口
 * @author hjc
 */
public interface UserService extends IService<User> {
    void saveUserDetails(String username, String password);
    void test(List<String> list);
}