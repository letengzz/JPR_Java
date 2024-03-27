package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

@SpringBootTest
class Producer {

    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 测试事务消息
     * 默认是sync（同步的）
     * 事务消息会有确认和回查机制
     * 事务消息都会走到同一个监听回调里面，所以需要使用tag或者key来区分过滤
     *
     * @throws Exception
     */
    @Test
    public void testTransactionMsg() throws IOException {
        // 构建消息体
        Message<String> message = MessageBuilder.withPayload("这是一个事务消息").build();
        // 发送事务消息（同步的） 最后一个参数才是消息主题
        TransactionSendResult transaction = rocketMQTemplate.sendMessageInTransaction("TransactionTopicTest", message, "消息的参数");
        // 拿到本地事务状态
        System.out.println(transaction.getLocalTransactionState());
        // 挂起jvm，因为事务的回查需要一些时间
        System.in.read();
    }
}
