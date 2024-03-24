package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;

@SpringBootTest
class Producer {
    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送延迟消息
     */
    @Test
    public void testDelaySend(){
        // 构建消息对象
        Message<String> message = MessageBuilder.withPayload("延迟消息").build();
        // 发送一个延时消息，延迟等级为4级，也就是30s后被监听消费
        SendResult sendResult = rocketMQTemplate.syncSend("DelayTopicTest", message, 2000, 4);
        //打印时间
        System.out.println("发送时间："+new Date());
        System.out.println(sendResult.getSendStatus());
    }
}
