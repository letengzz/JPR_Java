package com.hjc.demo.service;

import com.hjc.demo.entity.Admin;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class MyService {
    public String testParams(@NotNull @Valid Admin admin) {
        return admin.toString();
    }
}
