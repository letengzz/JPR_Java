package com.hjc;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author hjc
 */
//@Getter
//@Setter
//@RequiredArgsConstructor //生成包含所有final字段和非final字段（如果存在）的构造器
//@NoArgsConstructor(force = true) //强制生成无参构造器
//@AllArgsConstructor(staticName = "of")
//@Getter(AccessLevel.NONE)  //添加到类上时，将为类中所有字段生成getter方法
//@ToString
//@ToString(includeFieldNames = false)
//@ToString(callSuper = true)
//@ToString(doNotUseGetters = true)
//@ToString
//@ToString(onlyExplicitlyIncluded = true)
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class Account {
    private int id;
    @ToString.Include
    private String name;

//    public String getName() {
//        return name + "同学";
//    }

    //    @Getter(lazy = true)
//    private final String name = "张三";
//    private final String name = initValue();

    //    private String initValue() {
//        System.out.println("我不希望在对象创建时就执行");
//        return "张三";
//    }
//    @ToString.Exclude
    @ToString.Include(rank = 1,name = "年龄")
    private int age;
//    @Getter(onMethod_ = {@Deprecated}) //添加到字段上时，只为该字段生成getter方法
//    private boolean gender;
}
