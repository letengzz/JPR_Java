package com.hjc.demo.dao;

import com.hjc.demo.pojo.Employee;

import java.util.List;

/**
 * 模拟操作数据库
 * @author hjc
 */
public class EmployeeDao {
    public void save(List<Employee> list){
        System.out.println(list.size() + "模拟操作数据库....");
    }
}
