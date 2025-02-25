package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 岗位表
 * @TableName sys_post
 */
@TableName(value ="sys_post")
public class SysPost implements Serializable {
    /**
     * 岗位ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 显示顺序
     */
    private BigDecimal postSort;

    /**
     * 岗位状态（0、正常；1、停用）
     */
    private String postStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 岗位ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 岗位编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 岗位编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 岗位名称
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 岗位名称
     */
    public void setPostName(String postName) {
        this.postName = postName;
    }

    /**
     * 显示顺序
     */
    public BigDecimal getPostSort() {
        return postSort;
    }

    /**
     * 显示顺序
     */
    public void setPostSort(BigDecimal postSort) {
        this.postSort = postSort;
    }

    /**
     * 岗位状态（0、正常；1、停用）
     */
    public String getPostStatus() {
        return postStatus;
    }

    /**
     * 岗位状态（0、正常；1、停用）
     */
    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }
}