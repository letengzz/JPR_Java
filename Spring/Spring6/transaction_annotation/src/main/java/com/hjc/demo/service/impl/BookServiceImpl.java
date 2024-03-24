package com.hjc.demo.service.impl;

import com.hjc.demo.dao.BookDao;
import com.hjc.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    //TODO 只读
//    @Transactional(readOnly = true)
    //TODO 超时 超时时间单位秒
//    @Transactional(timeout = 3)
    //TODO 回滚策略
//    @Transactional(noRollbackFor = ArithmeticException.class)
//    @Transactional(noRollbackForClassName = "java.lang.ArithmeticException")
    //TODO 支持当前事务，如果不存在就新建一个
//    @Transactional(propagation = Propagation.REQUIRED)

    //TODO 开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //ToDO 默认
//    @Transactional
    @Override
    public void buyBook(Integer bookId, Integer userId) {
        //TODO 测试超时打开
//        try {
//            //延迟5s
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.updateStock(bookId);
        //更新用户的余额
        bookDao.updateBalance(userId, price);
        // TODO 测试回滚策略打开
//        System.out.println(1/0);
    }
}