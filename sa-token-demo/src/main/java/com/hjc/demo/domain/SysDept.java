package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 部门表
 * @TableName sys_dept
 */
@TableName(value ="sys_dept")
public class SysDept implements Serializable {
    /**
     * 部门id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Long deptSort;

    /**
     * 负责人
     */
    private String admin;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态（0、正常；1、停用）
     */
    private String deptStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    public Long getId() {
        return id;
    }

    /**
     * 部门id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 父部门id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父部门id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 祖级列表
     */
    public String getAncestors() {
        return ancestors;
    }

    /**
     * 祖级列表
     */
    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    /**
     * 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 显示顺序
     */
    public Long getDeptSort() {
        return deptSort;
    }

    /**
     * 显示顺序
     */
    public void setDeptSort(Long deptSort) {
        this.deptSort = deptSort;
    }

    /**
     * 负责人
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * 负责人
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     * 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 部门状态（0、正常；1、停用）
     */
    public String getDeptStatus() {
        return deptStatus;
    }

    /**
     * 部门状态（0、正常；1、停用）
     */
    public void setDeptStatus(String deptStatus) {
        this.deptStatus = deptStatus;
    }
}