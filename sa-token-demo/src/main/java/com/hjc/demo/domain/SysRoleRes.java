package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 角色和资源关联表
 * @TableName sys_role_res
 */
@TableName(value ="sys_role_res")
public class SysRoleRes implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 资源id
     */
    private Long resId;

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
     * 资源id
     */
    public Long getResId() {
        return resId;
    }

    /**
     * 资源id
     */
    public void setResId(Long resId) {
        this.resId = resId;
    }
}