package com.hjc.demo.controller;

import com.hjc.demo.entity.UserBorrowDetail;
import com.hjc.demo.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjc
 */
@RestController
public class BorrowController {
    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail findUserBorrows(@PathVariable("uid") Integer uid){
        return service.getUserBorrowDetailByUid(uid);
    }
}
