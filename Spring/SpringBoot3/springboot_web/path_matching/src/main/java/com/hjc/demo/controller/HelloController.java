package com.hjc.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    /**
     * 默认使用 PathPatternParser 进行路径匹配
     * 不能匹配 **在中间的情况，剩下的和 AntPathMatcher 语法兼容
     */
    @GetMapping("/a*/**/b?/{p1:[a-f]+}")
    public String hello(HttpServletRequest request, @PathVariable("p1")String p1){
        log.info("路径变量p1： {}", p1);
        //获取请求路径
        String uri = request.getRequestURI();
        return uri;
    }
}
