package com.hjc.demo.service.Impl;

import com.hjc.demo.entity.Book;
import com.hjc.demo.entity.Borrow;
import com.hjc.demo.entity.User;
import com.hjc.demo.entity.UserBorrowDetail;
import com.hjc.demo.mapper.BorrowMapper;
import com.hjc.demo.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper mapper;

    //RestTemplate支持多种方式的远程调用
    @Resource
    private RestTemplate restTemplate;



    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(Integer uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        //调用其他关联信息

        //获取User信息
        //这里通过调用getForObject来请求其他服务，并将结果自动进行封装
        User user = restTemplate.getForObject("http://localhost:8101/user/" + uid, User.class);

        //获取每一本书的详细信息
        List<Book> books = borrow.stream().map(borrow1 ->
                        restTemplate.getForObject("http://localhost:8201/book/" + borrow1.getBid(), Book.class))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user,books);
    }
}
