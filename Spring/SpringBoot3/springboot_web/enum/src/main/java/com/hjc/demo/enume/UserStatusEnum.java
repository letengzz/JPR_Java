package com.hjc.demo.enume;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 用户状态枚举
 *
 * @author hjc
 */

@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserStatusEnum {
    //0:启用 1:禁用 2:删除 3:锁定
    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),
    DELETE(2, "删除"),
    LOCK(3, "锁定");

//    @JsonValue
    private int code;
    private String message;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
