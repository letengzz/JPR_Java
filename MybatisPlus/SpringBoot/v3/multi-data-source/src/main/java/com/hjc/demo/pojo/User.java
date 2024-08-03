package com.hjc.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data //lombok注解
@TableName("t_user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}