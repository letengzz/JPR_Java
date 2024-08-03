package com.hjc.demo.entity;

import com.hjc.demo.enume.UserStatusEnum;
import lombok.Data;

/**
 * 用户实体类
 * @author hjc
 */
@Data
public class SysUser {
    private Long id;
    private String username;
    private String nickname;
    //0:启用 1:禁用 2:删除 3:锁定
    private UserStatusEnum status;
}
