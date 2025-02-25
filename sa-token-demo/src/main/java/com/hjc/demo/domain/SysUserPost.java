package com.hjc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 用户和岗位关联表
 * @TableName sys_user_post
 */
@TableName(value ="sys_user_post")
public class SysUserPost implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 岗位id
     */
    private Long postId;

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
     * 岗位id
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * 岗位id
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }
}