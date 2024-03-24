package com.hjc.demo.service.impl;

import com.hjc.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @author hjc
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public void addUser(String username, String password) {
        UserDetailsManager manager = (UserDetailsManager) userDetailsService;
        manager.createUser(User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("ADMIN", "USER")
                .build());
    }

    @Override
    public void delUser(String username) {
        UserDetailsManager manager = (UserDetailsManager) userDetailsService;
        manager.deleteUser(username);
    }

    @Override
    public boolean updateUser(String username, String password) {
        UserDetailsManager manager = (UserDetailsManager) userDetailsService;
        try {
            manager.updateUser(
                    User.withDefaultPasswordEncoder()
                            .username(username) //自定义用户名
                            .password(password) //自定义密码
                            .roles("USER")  //自定义角色
                            .build()
            );
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void updatePass(String oldPassword, String newPassword) {
        UserDetailsManager manager = (UserDetailsManager) userDetailsService;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        manager.changePassword(oldPassword, "{bcrypt}" + encoder.encode(newPassword));
    }


    @Override
    public boolean existsUser(String username) {
        UserDetailsManager manager = (UserDetailsManager) userDetailsService;
        return manager.userExists(username);
    }
}