package com.hjc.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 管理员
 * @author hjc
 */
@Schema(title = "管理员")
@Data
public class Admin {
    @Schema(title = "管理员ID")
    private Integer id;

    @Schema(title = "管理员姓名")
    private String name;

    @Schema(title = "管理的用户")
    private List<User> users;
}
