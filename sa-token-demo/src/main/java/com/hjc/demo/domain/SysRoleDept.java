package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 角色和部门关联表
 * @TableName sys_role_dept
 */
@TableName(value ="sys_role_dept")
public class SysRoleDept implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 部门id
     */
    private Long deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 部门id
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 部门id
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}