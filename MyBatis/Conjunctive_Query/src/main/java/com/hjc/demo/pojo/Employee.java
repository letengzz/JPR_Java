package com.hjc.demo.pojo;

/**
 * 雇员实体类
 * @author hjc
 */
public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer deptId;

    /**
     * 设置多对一关联
     */
    private Department dept;

    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, Integer deptId, Department dept) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.deptId = deptId;
        this.dept = dept;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", deptId=" + deptId +
                ", dept=" + dept +
                '}';
    }
}