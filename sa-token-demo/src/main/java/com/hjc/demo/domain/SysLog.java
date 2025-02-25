package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 * @TableName sys_log
 */
@TableName(value ="sys_log")
public class SysLog implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 请求地址
     */
    private String url;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 业务模块名称
     */
    private String businessName;

    /**
     * 方法名
     */
    private String method;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 操作状态（0正常 1异常）
     */
    private String logStatus;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 创建时间
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 操作人员
     */
    public String getOperName() {
        return operName;
    }

    /**
     * 操作人员
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * 请求参数
     */
    public String getOperParam() {
        return operParam;
    }

    /**
     * 请求参数
     */
    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    /**
     * 请求地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 请求地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 业务模块名称
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * 业务模块名称
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * 方法名
     */
    public String getMethod() {
        return method;
    }

    /**
     * 方法名
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 返回结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 返回结果
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 操作状态（0正常 1异常）
     */
    public String getLogStatus() {
        return logStatus;
    }

    /**
     * 操作状态（0正常 1异常）
     */
    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    /**
     * 错误信息
     */
    public String getError() {
        return error;
    }

    /**
     * 错误信息
     */
    public void setError(String error) {
        this.error = error;
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
}