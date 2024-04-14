package com.hjc.demo.client.fallback;

import com.hjc.demo.client.BookClient;
import com.hjc.demo.entity.Book;
import org.springframework.stereotype.Component;

@Component   //注意，需要将其注册为Bean，Feign才能自动注入
public class BookFallbackClient implements BookClient {
    @Override
    public Book getBookById(int bid) {
        Book book = new Book();
        book.setDesc("服务暂时不可用");
        book.setTitle("服务降级");
        return book;
    }
}
