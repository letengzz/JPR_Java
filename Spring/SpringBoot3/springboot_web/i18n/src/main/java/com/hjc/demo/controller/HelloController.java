package com.hjc.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

/**
 * @author hjc
 */
@Controller
@Slf4j
public class HelloController {
    @Autowired  //国际化取消息用的组件
    private MessageSource messageSource;

    @GetMapping("/haha")
    public String haha(HttpServletRequest request){
        Locale locale =
                request.getLocale();
        //利用代码的方式获取国际化配置文件中指定的配置项的值
        String login = messageSource.getMessage("login", null, locale);
        log.info(login);
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
