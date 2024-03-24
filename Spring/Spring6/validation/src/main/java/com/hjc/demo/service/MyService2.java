package com.hjc.demo.service;

import com.hjc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.BindException;


@Service
public class MyService2 {
    @Autowired
    private Validator validator;

    public boolean validaPersonByValidator(User user) {
        BindException bindException = new BindException(user, user.getName());
        validator.validate(user,bindException);
        return bindException.hasErrors();
    }
}
