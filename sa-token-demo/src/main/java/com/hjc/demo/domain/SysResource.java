package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资源表
 * @TableName sys_resource
 */
@TableName(value ="sys_resource")
public class SysResource implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 父级ids
     */
    private String parentIds;

    /**
     * 标题（目录名称、菜单名称、按钮名称）
     */
    private String title;

    /**
     * 类型（1、目录；2、菜单；3、按钮）
     */
    private String type;

    /**
     * 权限标识（菜单和按钮）
     */
    private String permission;

    /**
     * 路由地址（目录和菜单）
     */
    private String routePath;

    /**
     * 菜单组件名称
     */
    private String componentName;

    /**
     * 菜单组件地址
     */
    private String componentPath;

    /**
     * 状态（0、正常；1、禁用）
     */
    private String resStatus;

    /**
     * 排序
     */
    private BigDecimal resSort;

    /**
     * 外链菜单（1：是；2：否）
     */
    private String menuExtFlag;

    /**
     * 菜单缓存（1：是；2：否）
     */
    private String menuCacheFlag;

    /**
     * 菜单和目录可见（1：是；2：否）
     */
    private String menuHiddenFlag;

    /**
     * 菜单图标
     */
    private String menuIcon;

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
     * 父级ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 父级ids
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 标题（目录名称、菜单名称、按钮名称）
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题（目录名称、菜单名称、按钮名称）
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 类型（1、目录；2、菜单；3、按钮）
     */
    public String getType() {
        return type;
    }

    /**
     * 类型（1、目录；2、菜单；3、按钮）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 权限标识（菜单和按钮）
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 权限标识（菜单和按钮）
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 路由地址（目录和菜单）
     */
    public String getRoutePath() {
        return routePath;
    }

    /**
     * 路由地址（目录和菜单）
     */
    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    /**
     * 菜单组件名称
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * 菜单组件名称
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * 菜单组件地址
     */
    public String getComponentPath() {
        return componentPath;
    }

    /**
     * 菜单组件地址
     */
    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    /**
     * 状态（0、正常；1、禁用）
     */
    public String getResStatus() {
        return resStatus;
    }

    /**
     * 状态（0、正常；1、禁用）
     */
    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    /**
     * 排序
     */
    public BigDecimal getResSort() {
        return resSort;
    }

    /**
     * 排序
     */
    public void setResSort(BigDecimal resSort) {
        this.resSort = resSort;
    }

    /**
     * 外链菜单（1：是；2：否）
     */
    public String getMenuExtFlag() {
        return menuExtFlag;
    }

    /**
     * 外链菜单（1：是；2：否）
     */
    public void setMenuExtFlag(String menuExtFlag) {
        this.menuExtFlag = menuExtFlag;
    }

    /**
     * 菜单缓存（1：是；2：否）
     */
    public String getMenuCacheFlag() {
        return menuCacheFlag;
    }

    /**
     * 菜单缓存（1：是；2：否）
     */
    public void setMenuCacheFlag(String menuCacheFlag) {
        this.menuCacheFlag = menuCacheFlag;
    }

    /**
     * 菜单和目录可见（1：是；2：否）
     */
    public String getMenuHiddenFlag() {
        return menuHiddenFlag;
    }

    /**
     * 菜单和目录可见（1：是；2：否）
     */
    public void setMenuHiddenFlag(String menuHiddenFlag) {
        this.menuHiddenFlag = menuHiddenFlag;
    }

    /**
     * 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
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