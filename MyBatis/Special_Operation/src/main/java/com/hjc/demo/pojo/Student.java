package com.hjc.demo.pojo;

/**
 * Student实体类
 * @author hjc
 */
public class Student {
    private Integer id;
    private String sName;
    private Integer age;
    private String sex;

    //构造方法

    public Student() {
    }

    public Student(Integer id, String sName, Integer age, String sex) {
        this.id = id;
        this.sName = sName;
        this.age = age;
        this.sex = sex;
    }

    //getter和setter方法

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sName='" + sName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}