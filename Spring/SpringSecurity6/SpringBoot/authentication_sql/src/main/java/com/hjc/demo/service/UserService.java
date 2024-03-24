package com.hjc.demo.service;

public interface UserService {
    void addUser(String username, String password);

    void delUser(String username);

    boolean  updateUser(String username, String password);

    void  updatePass( String oldPassword,String newPassword);

    boolean existsUser(String username);
}
