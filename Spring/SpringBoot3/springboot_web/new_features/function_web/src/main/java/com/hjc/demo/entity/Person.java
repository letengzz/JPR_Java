package com.hjc.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
}