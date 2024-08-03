package com.hjc.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * User实体类
 * @author hjc
 */
@Data //lombok注解
@TableName("user") //标识实体类对应的表
public class User {
    //将属性所对应的字段指定为主键
    //@TableId注解的value属性用于指定主键的字段
    //@TableId注解的type属性用于指定主键的主键策略
    @TableId(value = "id",type = IdType.AUTO)
    private Long uid;
    @TableField("name")
    private String username;
    private Integer age;
    private String email;

    //添加逻辑删除属性 value默认逻辑未删除值 delval默认逻辑删除值
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;
}