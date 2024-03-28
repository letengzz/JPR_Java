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
     * 测试发送消息
     *
     * @throws Exception
     */
    @Test
    public void testClusterMsg(){
        // 往主题里面发送一个简单的字符串消息
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSend("ModeClusterTopicTest", "我是消息" + i);
        }
    }

    @Test
    public void testBroadMsg(){
        // 往主题里面发送一个简单的字符串消息
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSend("ModeBroadTopicTest", "我是消息" + i);
        }
    }
}
