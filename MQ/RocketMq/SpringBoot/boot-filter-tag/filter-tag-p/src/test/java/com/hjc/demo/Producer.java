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
     * 发送带标签(Tag)的消息
     */
    @Test
    public void testTagMsg(){
        // 发送一个tag为tagA的数据
        SendResult sendResult = rocketMQTemplate.syncSend("TagTopicTest:tagA", "我是一个带标记的消息A");
        System.out.println(sendResult.getSendStatus());
    }
}
