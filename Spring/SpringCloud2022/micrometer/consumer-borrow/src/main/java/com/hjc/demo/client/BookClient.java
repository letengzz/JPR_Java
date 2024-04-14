package com.hjc.demo.client;

import com.hjc.demo.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hjc
 */
@FeignClient(value = "book-service",path = "/book")
public interface BookClient {

    @RequestMapping("/{bid}")
    Book getBookById(@PathVariable("bid") int bid);
}