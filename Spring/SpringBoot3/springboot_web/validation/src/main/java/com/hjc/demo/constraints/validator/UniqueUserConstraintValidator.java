package com.hjc.demo.constraints.validator;

import com.hjc.demo.constraints.UniqueUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * UniqueUser注解的验证器 负责校验@UniqueUser注解的String类型字段
 * @author hjc
 */
@Component
@Scope("prototype") //每次用的时候都创建一个新的实例。防止私有变量线程安全问题
public class UniqueUserConstraintValidator implements ConstraintValidator<UniqueUser, String>{

    //模拟已存在用户
    List<String> names = Arrays.asList("张三三", "李四四", "王五五");
    private String[] baned;
    //初始化，用于获取注解标注的信息
    @Override
    public void initialize(UniqueUser constraintAnnotation) {
        //获取注解中的信息
        baned = constraintAnnotation.baned();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //统计写的用户名是否在指定的黑名单中
        long count = Arrays.stream(baned).filter(s -> s.equals(value)).count();
        return !names.contains(value) && count == 0L;
    }
}
