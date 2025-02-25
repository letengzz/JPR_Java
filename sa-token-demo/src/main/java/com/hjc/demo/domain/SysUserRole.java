package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 用户和角色关联表
 * @TableName sys_user_role
 */
@TableName(value ="sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
}