package com.hjc.demo.controller;

import com.hjc.demo.service.BookService;
import com.hjc.demo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CheckoutService checkoutService;
    public void buyBook(Integer bookId, Integer userId){
        bookService.buyBook(bookId, userId);
    }

    public void checkout(Integer[] bookIds, Integer userId){
        checkoutService.checkout(bookIds, userId);
    }
}
