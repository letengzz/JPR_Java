package com.hjc.demo.controller;



import com.hjc.demo.entity.Admin;
import com.hjc.demo.entity.User;
import com.hjc.demo.entity.dto.AdminDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员
 *
 * @author hjc
 */
@Tag(name = "管理员", description = "管理员接口")
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * 分组设置
     * @param dto
     * @return
     */
    @Operation(summary = "获取管理员信息", description = "获取管理员信息的方法")
    @PostMapping("/list")
    public Admin list(@RequestBody AdminDto dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setName(dto.getName());
        admin.setUsers(List.of(new User(1, "test1", "123456"),
                new User(2, "test2", "123456")));
        return admin;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringBoot-Swagger API")
                        .description("测试接口文档")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("HJC")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
