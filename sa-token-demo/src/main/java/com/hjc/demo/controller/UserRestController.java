package com.hjc.demo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.hjc.demo.domain.SysUserInfo;
import com.hjc.demo.domain.vo.req.LoginVo;
import com.hjc.demo.service.SysUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author hjc
 */
@Tag(name = "用户管理", description = "用户管理")//swagger注解
@RequestMapping("/user")
@RestController
public class UserRestController {

    @Resource
    private SysUserInfoService sysUserInfoService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public SaResult login(@RequestBody LoginVo loginVo) {
        //去数据库查询用户信息
        Optional<SysUserInfo> sysUserInfo = sysUserInfoService.lambdaQuery()
                .eq(SysUserInfo::getUsername, loginVo.getUsername())
                .eq(SysUserInfo::getPassword, SaSecureUtil.sha256(loginVo.getPassword())).oneOpt();
        if(sysUserInfo.isEmpty()){
            return SaResult.error("用户名或密码错误");
        }
        // 会话登录：参数填写要登录的账号id，建议的数据类型：long | int | String， 不可以传入复杂类型，如：User、Admin 等等
        //模拟登录 自动生成访问令牌等信息
        StpUtil.login(sysUserInfo.get().getId());

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 登录成功，返回token信息给前端，前端自己保存，访问带上就行
        //前端不分离：服务端渲染页面
        //          自动让浏览器把令牌信息 写入 Cookie ：Cookie 名叫satoken 值就是令牌
        //          浏览器默认每个请求都会带上这个Cookie
        //前端分离：前端渲染页面
        //          把令牌的name=value的json交给前端，前端自己保存，访问带到请求头中
        return SaResult.ok("登录成功").setData(tokenInfo);
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public SaResult logout() {
        // 不用接参数 不用调自己的业务代码，用户就能直接退出
        //1、只要登录成功，以后的每个请求都会带上token
        //2、satoken 框架自动解析来自于请求头的token，并自动去redis中查询这个用户信息
        //3、退出
        //      后端： 清楚redis。以后即时带上了老令牌，redis中没有就视为没登录
        //      前端： 不分离：清除cookie。分离：清除前端保存的token
        StpUtil.logout();
        return SaResult.ok("登出成功");
    }


    @SaCheckLogin // 验证登录
    @Operation(summary = "创建订单")
    @GetMapping("/create/order")
    public SaResult createOrder() {
        //编码式
        //查看所有权限
        List<String> allPermission = StpUtil.getPermissionList();

        boolean b = StpUtil.hasPermission("order.create");
        if (b) {
            return SaResult.ok("创建订单成功");

        }
        return SaResult.error("没有权限");
    }
}
