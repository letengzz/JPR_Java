package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 角色表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 角色状态（0、正常；1、禁用）
     */
    private String roleStatus;

    /**
     * 数据范围（1、全部数据权限；2、自定数据权限；3、本部门数据权限；4、本部门及以下数据权限）
     */
    private String dataScope;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    public Long getId() {
        return id;
    }

    /**
     * 角色id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 角色权限字符串
     */
    public String getRoleKey() {
        return roleKey;
    }

    /**
     * 角色权限字符串
     */
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    /**
     * 角色状态（0、正常；1、禁用）
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * 角色状态（0、正常；1、禁用）
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * 数据范围（1、全部数据权限；2、自定数据权限；3、本部门数据权限；4、本部门及以下数据权限）
     */
    public String getDataScope() {
        return dataScope;
    }

    /**
     * 数据范围（1、全部数据权限；2、自定数据权限；3、本部门数据权限；4、本部门及以下数据权限）
     */
    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }
}