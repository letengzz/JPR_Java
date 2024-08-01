package com.hjc.demo.po;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Account {
    @Length(min = 3)   //只需要在对应的字段上添加校验的注解即可
    String username;
    @Length(min = 10)
    String password;
}
