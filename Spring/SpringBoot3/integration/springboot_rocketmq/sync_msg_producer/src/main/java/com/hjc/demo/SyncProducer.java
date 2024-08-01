package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * @author hjc
 */
@SpringBootTest
public class SyncProducer {
    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送同步消息
     */
    @Test
    void testSyncSend() {
        // 往SyncTopicTest的主题里面发送一个简单的字符串消息
        SendResult sendResult = rocketMQTemplate.syncSend("SyncTopicTest", "发送一个同步消息");
        // 拿到消息的发送状态
        System.out.println(sendResult.getSendStatus());
        // 拿到消息的id
        System.out.println(sendResult.getMsgId());
    }
}
