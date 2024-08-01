package com.hjc.demo.controller;

import com.hjc.demo.entity.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @RequestMapping("/person")
    public Person person(){
        Person person = new Person();
        person.setId(1L);
        person.setUserName("张三");
        person.setEmail("111@qq.com");
        person.setAge(22);
        return person;
    }
}
