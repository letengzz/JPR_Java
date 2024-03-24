package com.hjc.demo.service;

import com.hjc.demo.entity.Admin;
import com.hjc.demo.entity.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class MsgService {
    public String testParams(@NotNull @Valid Message msg) {
        return msg.toString();
    }
}
