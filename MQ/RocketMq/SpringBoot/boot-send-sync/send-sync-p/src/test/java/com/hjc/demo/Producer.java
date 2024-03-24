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
     * 发送同步消息
     *
     * @throws Exception
     */
    @Test
    public void testSyncSend(){
        // 往simpleTopicTest的主题里面发送一个简单的字符串消息
        SendResult sendResult = rocketMQTemplate.syncSend("SyncTopicTest", "发送一个同步消息");
        // 拿到消息的发送状态
        System.out.println(sendResult.getSendStatus());
        // 拿到消息的id
        System.out.println(sendResult.getMsgId());
    }
}
