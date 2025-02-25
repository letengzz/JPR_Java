package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName sys_user_info
 */
@TableName(value ="sys_user_info")
public class SysUserInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型（0、管理员；1、普通用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 性别（0、男；1、女；2、保密）
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 帐号状态（0、正常；1、禁用）
     */
    private String userStatus;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 删除标识
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户id
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 用户类型（0、管理员；1、普通用户）
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类型（0、管理员；1、普通用户）
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 手机号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 性别（0、男；1、女；2、保密）
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性别（0、男；1、女；2、保密）
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 帐号状态（0、正常；1、禁用）
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 帐号状态（0、正常；1、禁用）
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 删除标识
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标识
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}