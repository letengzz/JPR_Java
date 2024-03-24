package com.hjc.demo.pojo;

import java.util.List;

/**
 * 部门实体类
 * @author hjc
 */
public class Department {
    private Integer deptId;
    private String deptName;
    private String deptCity;

    /**
     * 设置一对多关联
     */
    private List<Employee> employees;

    public Department() {
    }

    public Department(Integer deptId, String deptName, String deptCity, List<Employee> employees) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptCity = deptCity;
        this.employees = employees;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCity() {
        return deptCity;
    }

    public void setDeptCity(String deptCity) {
        this.deptCity = deptCity;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptCity='" + deptCity + '\'' +
                ", employees=" + employees +
                '}';
    }
}