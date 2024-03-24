package com.hjc.demo.validation;

import com.hjc.demo.entity.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class PersonValidator implements Validator {

    /**
     * todo 用来表示此校验用在哪个类型上
     * @param clazz the {@link Class} that this {@link Validator} is
     * being asked if it can {@link #validate(Object, Errors) validate}
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    /**
     * todo 设置校验逻辑的地点
     * @param object the object that is to be validated
     * @param errors contextual state about the validation process
     */
    @Override
    public void validate(Object object, Errors errors) {
        //ValidationUtils，是Spring封装的校验工具类，帮助快速实现校验。
        //todo name不能为空
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty","name is null");
        //todo age 不能小于0 不能大于200
        Person p = (Person) object;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "age.value.error","error value < 0");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "age.value.error","error value too old");
        }
    }
}