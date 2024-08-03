package com.hjc.demo.pojo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjc.demo.enums.AgeEnum;
import com.hjc.demo.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hjc
 */
@AllArgsConstructor
@NoArgsConstructor
@Data //lombok注解
@TableName("t_stu")
public class Student {
    private Long id;
    private String name;
    private AgeEnum age;
    private String email;
    //将注解所标识的属性的值存储到数据库中
    @EnumValue
    private SexEnum sex;
}
