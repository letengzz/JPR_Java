package com.hjc.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author hjc
 */
@Getter
public enum SexEnum {
    /**
     * 1：男 2：女
     */
    MALE(1,"男"),
    FEMALE(2,"女");

    @EnumValue
    private final Integer sex;
    private final String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
