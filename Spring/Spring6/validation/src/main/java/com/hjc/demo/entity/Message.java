package com.hjc.demo.entity;

import com.hjc.demo.annotation.CannotBlank;

public class Message {

    @CannotBlank
    private String msgName;

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }
}
