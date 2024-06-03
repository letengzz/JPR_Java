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
    private int id;
    private String name;
    private Date date;
    private double salary;
}