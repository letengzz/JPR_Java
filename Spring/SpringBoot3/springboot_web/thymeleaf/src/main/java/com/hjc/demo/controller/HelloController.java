package com.hjc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 适配 服务端渲染 前后不分离模式开始
public class HelloController {
    /**
     * 利用模板引擎跳转到指定页面
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name,
                        Model model){
        String text = "<span style='color:red'>"+name+"</span>";
        //把需要给页面共享的数据放到model中
        model.addAttribute("name",name);
        model.addAttribute("name2",text);
        //路径是动态的
        model.addAttribute("imgUrl","/1.png");
        //数据库查出的样式
        model.addAttribute("style","width: 300px;height: 300px");
        //是否显示
        model.addAttribute("show","false");
        //模板的逻辑视图名
        //物理视图 = 前缀 + 逻辑视图名 + 后缀
        //真实地址: classpath:/templates/hello.html
        return "hello";
    }
}
