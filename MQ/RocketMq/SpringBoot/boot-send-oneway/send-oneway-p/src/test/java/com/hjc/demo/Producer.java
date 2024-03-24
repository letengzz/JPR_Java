package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Producer {


    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送单向消息
     */
    @Test
    public void testOnewaySend(){
        // 发送单向消息，没有返回值和结果
        rocketMQTemplate.sendOneWay("OnewayTopicTest","我是一个单向消息");
        System.out.println("执行完毕");
    }
}
