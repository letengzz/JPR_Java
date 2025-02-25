package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 字典数据表
 * @TableName sys_dict_data
 */
@TableName(value ="sys_dict_data")
public class SysDictData implements Serializable {
    /**
     * 字典id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 状态（0正常 1停用）
     */
    private String dictStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    public Long getId() {
        return id;
    }

    /**
     * 字典id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 父级id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父级id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 字典标签
     */
    public String getDictLabel() {
        return dictLabel;
    }

    /**
     * 字典标签
     */
    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    /**
     * 字典键值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 字典键值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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
     * 字典排序
     */
    public Integer getDictSort() {
        return dictSort;
    }

    /**
     * 字典排序
     */
    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    /**
     * 样式属性（其他样式扩展）
     */
    public String getCssClass() {
        return cssClass;
    }

    /**
     * 样式属性（其他样式扩展）
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    /**
     * 表格回显样式
     */
    public String getListClass() {
        return listClass;
    }

    /**
     * 表格回显样式
     */
    public void setListClass(String listClass) {
        this.listClass = listClass;
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