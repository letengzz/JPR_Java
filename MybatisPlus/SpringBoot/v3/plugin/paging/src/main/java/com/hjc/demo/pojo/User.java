package com.hjc.demo.pojo;

import lombok.Data;

/**
 * User实体类
 * @author hjc
 */
@Data //lombok注解
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}