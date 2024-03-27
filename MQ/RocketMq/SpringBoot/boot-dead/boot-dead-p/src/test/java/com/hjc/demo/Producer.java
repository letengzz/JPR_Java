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
     * 发送消息
     *
     * @throws Exception
     */
    @Test
    public void testDeadMsg(){
        String key = UUID.randomUUID().toString();
        System.out.println("key = " + key);
        Message<String> message = MessageBuilder.withPayload("死信队列")
                .setHeader(RocketMQHeaders.KEYS, key)
                .build();
        // 往主题里面发送一个简单的字符串消息
        rocketMQTemplate.syncSend("DeadTopicTest:tagA", message);
        System.out.println("发送成功");
    }
}
