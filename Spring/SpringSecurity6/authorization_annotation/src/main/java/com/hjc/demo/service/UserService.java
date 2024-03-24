package com.hjc.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @PreAuthorize("hasAnyRole('user')")
    public void test(){
        System.out.println("成功执行");
    }

    @PreFilter("filterObject.equals('hjc')")   //filterObject代表集合中每个元素，只要满足条件的元素才会留下
    public void test(List<String> list){
        System.out.println("成功执行"+list);
    }

    @PreFilter(value = "filterObject.equals('hjc')", filterTarget = "list2")
    public void test(List<String> list, List<String> list2){
        System.out.println("成功执行"+list);
    }
}