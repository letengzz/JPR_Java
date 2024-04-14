package com.hjc.demo.service;

import com.hjc.demo.entity.UserBorrowDetail;

public interface BorrowService {
    UserBorrowDetail getUserBorrowDetailByUid(Integer uid);
}
