package com.hjc.demo.pojo;

/**
 * 数据库表 company 对应的实体类
 * @author hjc
 */
public class Company {
    private Integer id;
    private String cName;
    private String email;

    public Company() {
    }

    public Company(Integer id, String cName, String email) {
        this.id = id;
        this.cName = cName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}