package com.hjc.demo.controller;

import com.hjc.demo.po.Account;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

//@Validated   //首先在Controller上开启接口校验
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


//    @ResponseBody
//    @PostMapping("/submit")  //在参数上添加@Valid注解表示需要验证
//    public String submit(@Valid Account account){
////    public String submit(@Validated Account account){   //直接使用对象接收
//        System.out.println(account.getUsername().substring(3));
//        System.out.println(account.getPassword().substring(2, 10));
//        return "请求成功!";
//    }

    @ResponseBody
    @PostMapping("/submit")  //在参数上添加@Valid注解表示需要验证
    public String submit(@Valid Account account, BindingResult bindingResult) {
//    System.out.println(account.getUsername().substring(3));
//    System.out.println(account.getPassword().substring(2, 10));
        //判断是否发生校验错误
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            //获取所有的错误信息
            bindingResult.getFieldErrors().forEach((fieldError) -> {
                //获取发生错误的字段名
                String field = fieldError.getField();
                //获取错误信息
                String message = fieldError.getDefaultMessage();
                errors.put(field, message);
            });
            return errors.toString();
        }
        return "请求成功!";
    }
}
