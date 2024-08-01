package com.hjc.demo.controller;

import com.hjc.demo.po.Account;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Validated   //首先在Controller上开启接口校验
@Controller
public class TestController {
    //    @ResponseBody
//    @PostMapping("/submit")
//    public String submit(@Length(min = 3,message = "少于3位") String username,  //使用@Length注解一步到位
//                         @Length(min = 10) String password){
//        System.out.println(username.substring(3));
//        System.out.println(password.substring(2, 10));
//        return "请求成功!";
//    }


    @ResponseBody
    @PostMapping("/submit")  //在参数上添加@Valid注解表示需要验证
    public String submit(@Validated Account account){   //直接使用对象接收
        System.out.println(account.getUsername().substring(3));
        System.out.println(account.getPassword().substring(2, 10));
        return "请求成功!";
    }
}
