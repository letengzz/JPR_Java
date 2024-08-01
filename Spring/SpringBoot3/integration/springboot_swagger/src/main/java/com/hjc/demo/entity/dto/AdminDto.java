package com.hjc.demo.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "管理员")
@Data
public class AdminDto {
    @Schema(title = "管理员ID")
    private Integer id;

    @Schema(title = "管理员姓名")
    private String name;
}
