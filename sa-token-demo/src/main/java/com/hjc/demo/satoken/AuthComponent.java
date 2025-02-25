package com.hjc.demo.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @author hjc
 */
@Slf4j
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展 查询出的结果自动放到redis中，不整合redis会放到内存中
public class AuthComponent implements StpInterface {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private ObjectMapper objectMapper;

    @Override //本次登录查询一次，会话期间不重复查询
    public List<String> getPermissionList(Object loginId, String loginType) {
        //每次现场调，查询数据库，数据库压力比较大
        String permissionList = stringRedisTemplate.opsForValue().get("permissionList:" + loginId);
        List<String> list = new ArrayList<>();
        if (permissionList != null && permissionList.isBlank()) {
            try {
                list = objectMapper.readValue(permissionList, new TypeReference<List<String>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            //去resource表中根据角色、部门等查询出所有菜单、按钮、文件、数据的权限标识
            list = Arrays.asList("order.create", "order.delete", "order.update");

            try {
                stringRedisTemplate.opsForValue().set("permissionList:" + loginId, objectMapper.writeValueAsString(list));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("获取权限列表：{}", list);
        return list;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //每次现场调，查询数据库，数据库压力比较大
        String roleList = stringRedisTemplate.opsForValue().get("roleList:" + loginId);
        List<String> list = new ArrayList<>();
        if (roleList != null && roleList.isBlank()) {
            try {
                list = objectMapper.readValue(roleList, new TypeReference<List<String>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            //去resource表中根据角色、部门等查询出所有菜单、按钮、文件、数据的权限标识
            list = Arrays.asList("admin", "user");
            try {
                stringRedisTemplate.opsForValue().set("roleList:" + loginId, objectMapper.writeValueAsString(list));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("获取角色列表：{}", list);
        return list;
    }
}
