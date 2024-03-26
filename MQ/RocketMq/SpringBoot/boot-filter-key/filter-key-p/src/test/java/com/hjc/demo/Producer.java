package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@SpringBootTest
class Producer {


    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送一个带key的消息,我们使用事务消息 打断点查看消息头
     *
     * @throws Exception
     */
    @Test
    public void testKeyMsg() throws Exception {
        // 发送一个带有key的消息
        //key 要确保唯一 为了查阅和去重
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        Message<String> message = MessageBuilder.withPayload("我是一个带key的消息")
                .setHeader(RocketMQHeaders.KEYS, uuid)
                .build();
        SendResult sendResult = rocketMQTemplate.syncSend("KeyTopicTest", message);
        System.out.println("sendResult = " + sendResult);
    }
}
