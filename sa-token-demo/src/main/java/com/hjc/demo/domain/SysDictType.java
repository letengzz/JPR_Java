package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 字典类型表
 * @TableName sys_dict_type
 */
@TableName(value ="sys_dict_type")
public class SysDictType implements Serializable {
    /**
     * 字典主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String dictStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 字典主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 字典类型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 字典类型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * 状态（0正常 1停用）
     */
    public String getDictStatus() {
        return dictStatus;
    }

    /**
     * 状态（0正常 1停用）
     */
    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus;
    }
}