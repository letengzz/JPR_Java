package com.hjc.demo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

/**
 * @author hjc
 */

@Getter
public enum AgeEnum implements IEnum<Integer> {
    /**
     * 注意需要实现 IEnums 也需要扫描枚举包
     */
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    AgeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private final int value;
    private final String desc;
    /**
     * 枚举数据库存储值
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
