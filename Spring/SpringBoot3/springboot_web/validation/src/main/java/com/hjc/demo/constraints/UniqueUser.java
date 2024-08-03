package com.hjc.demo.constraints;

import com.hjc.demo.constraints.validator.UniqueUserConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author hjc
 */
@Documented
@Constraint(
        validatedBy = {UniqueUserConstraintValidator.class} // 指定校验器
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    // 禁止的用户名
    String[] baned() default {};

    String message() default "{jakarta.validation.constraints.NotEmpty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
