package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件表
 * @TableName sys_file_info
 */
@TableName(value ="sys_file_info")
public class SysFileInfo implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * md5
     */
    private String md5;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * url地址
     */
    private String url;

    /**
     * 来源平台： aliyun、qiniu、minio...
     */
    private String sourceType;

    /**
     * 创建时间
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 文件类型
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 文件类型
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 文件大小
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * 文件大小
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 来源平台： aliyun、qiniu、minio...
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 来源平台： aliyun、qiniu、minio...
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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