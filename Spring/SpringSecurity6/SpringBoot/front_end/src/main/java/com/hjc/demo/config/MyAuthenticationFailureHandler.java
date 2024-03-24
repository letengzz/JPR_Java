package com.hjc.demo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;



import java.io.IOException;
import java.util.HashMap;

/**
 * @author hjc
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        //获取错误信息
        String localizedMessage = exception.getLocalizedMessage();

        //创建结果对象
        HashMap result = new HashMap();
        result.put("code", -1);
//        result.put("message", localizedMessage);
        if(exception instanceof BadCredentialsException){
            result.put("message", "密码不正确");
        }else if(exception instanceof DisabledException){
            result.put("message", "账号被禁用");
        }else if(exception instanceof UsernameNotFoundException){
            result.put("message", "用户名不存在");
        }else if(exception instanceof CredentialsExpiredException){
            result.put("message", "密码已过期");
        }else if(exception instanceof AccountExpiredException){
            result.put("message", "账号已过期");
        }else if(exception instanceof LockedException){
            result.put("message", "账号被锁定");
        }else{
            result.put("message", "未知异常");
        }

        //转换成json字符串
        String json = JSON.toJSONString(result);

        //返回响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
