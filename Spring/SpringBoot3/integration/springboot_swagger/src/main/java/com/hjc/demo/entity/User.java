package com.hjc.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 * @author hjc
 */
@Schema(title = "用户")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Schema(title = "用户ID")
    private Integer id;

    @Schema(title = "用户名")
    private String name;

    @Schema(title = "用户地址")
    private String address;
}
