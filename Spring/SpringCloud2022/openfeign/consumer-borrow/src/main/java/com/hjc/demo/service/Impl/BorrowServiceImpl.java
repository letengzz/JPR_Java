package com.hjc.demo.service.Impl;

import com.hjc.demo.client.BookClient;
import com.hjc.demo.client.UserClient;
import com.hjc.demo.entity.Book;
import com.hjc.demo.entity.Borrow;
import com.hjc.demo.entity.User;
import com.hjc.demo.entity.UserBorrowDetail;
import com.hjc.demo.mapper.BorrowMapper;
import com.hjc.demo.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper mapper;

    @Resource
    UserClient userClient;

    @Resource
    BookClient bookClient;

    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(Integer uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        User user = userClient.getUserById(uid);
//        List<Book> bookList = borrow
//                .stream()
//                .map(b -> bookClient.getBookById(b.getBid()))
//                .collect(Collectors.toList());
        List<Book> bookList = null;
        try {
            System.out.println("调用开始-----:"+  LocalDateTime.now());
            bookList = borrow
                    .stream()
                    .map(b -> bookClient.getBookById(b.getBid()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:"+ LocalDateTime.now());
        }
        return new UserBorrowDetail(user, bookList);
    }
}
