package com.hjc.demo.pojo;

import lombok.Data;

@Data
public class Account {
    int id;
    String username;
    String password;
    String authority;
    String role;
}