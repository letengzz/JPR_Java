package com.hjc.manager;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskManager {
    //第一个方法
    @XxlJob(value = "testJob")
    public void printInfo(){
        System.out.println("定时任务执行了" + new Date() );
    }
}
