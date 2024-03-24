package com.hjc.demo.service.impl;

import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @author hjc
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDetailsManager manager;

    @Resource
    private PasswordEncoder encoder;

    @Override
    public void addUser(String username, String password) {
        manager.createUser(User.withUsername(username)
                .password(encoder.encode(password)).roles("USER").build());
    }

    @Override
    public void delUser(String username){
        manager.deleteUser(username);
    }

    @Override
    public boolean updateUser(String username, String password) {
        try {
            manager.updateUser(User.withUsername(username)
                    .password(encoder.encode(password))
                    .authorities("ROLE_ADMIN")
                    .build());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void updatePass(String oldPassword, String newPassword) {
        manager.changePassword(oldPassword, encoder.encode(newPassword));
    }


    @Override
    public boolean existsUser(String username) {
        return manager.userExists(username);
    }
}
