package com.hjc.demo.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author hjc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    //成员变量
    //ExcelProperty 表示标题
    @ExcelProperty("员工编号")
    private int id;

    @ExcelProperty("员工姓名")
    private String name;

    @ExcelProperty("入职日期")
    private Date date;

    @ExcelProperty("员工工资")
    private double salary;

    // 忽略这个字段
    @ExcelIgnore
    private String ignore;
}