package com.hjc.task.manager;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskManager {

    /**
     * @Scheduled：方法是定时任务执行的
     * 每10秒执行一次定时任务方法taskPrintTime
     * 定时任务方法： 无参数 ，无返回值。
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void taskPrintTime(){
        System.out.println("定时任务执行方法："+new Date());
    }
}