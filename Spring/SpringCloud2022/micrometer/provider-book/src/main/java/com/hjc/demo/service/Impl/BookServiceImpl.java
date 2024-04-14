package com.hjc.demo.service.Impl;

import com.hjc.demo.entity.Book;
import com.hjc.demo.mapper.BookMapper;
import com.hjc.demo.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author hjc
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Override
    public Book getBookById(Integer bid) {
        return bookMapper.getBookById(bid);
    }
}
