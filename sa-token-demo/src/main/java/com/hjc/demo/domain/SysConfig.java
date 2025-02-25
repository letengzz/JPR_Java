package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 参数配置表 - 对象存储等的配置也可以放在这里
 * @TableName sys_config
 */
@TableName(value ="sys_config")
public class SysConfig implements Serializable {
    /**
     * 参数id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 0: 系统内置
     */
    private String configType;

    /**
     * 配置分组
     */
    private String configGroup;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 参数id
     */
    public Long getId() {
        return id;
    }

    /**
     * 参数id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数名称
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * 参数名称
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * 参数键名
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * 参数键名
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * 参数键值
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 参数键值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * 0: 系统内置
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 0: 系统内置
     */
    public void setConfigType(String configType) {
        this.configType = configType;
    }

    /**
     * 配置分组
     */
    public String getConfigGroup() {
        return configGroup;
    }

    /**
     * 配置分组
     */
    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup;
    }
}