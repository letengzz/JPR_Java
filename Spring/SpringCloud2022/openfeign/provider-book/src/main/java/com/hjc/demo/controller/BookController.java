package com.hjc.demo.controller;


import com.hjc.demo.entity.Book;
import com.hjc.demo.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hjc
 */
@RestController
public class BookController {
    @Resource
    BookService service;

    //这里以RESTFul风格为例
    @RequestMapping("/book/{uid}")
    public Book findUserById(@PathVariable("uid") Integer uid){
        //测试feign超时时间
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return service.getBookById(uid);
    }
}
