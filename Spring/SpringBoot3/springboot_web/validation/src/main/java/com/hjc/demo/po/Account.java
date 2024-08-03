package com.hjc.demo.po;

import com.hjc.demo.constraints.UniqueUser;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author hjc
 */
@Data
public class Account {
    @Length(min = 3, message = "{username.not3}")   //只需要在对应的字段上添加校验的注解即可
    @UniqueUser(message = "{username.exist}",baned = {"admin", "username"}) //自定义注解
    String username;
    @Length(min = 10, message = "{password.not10}")
    String password;
}
