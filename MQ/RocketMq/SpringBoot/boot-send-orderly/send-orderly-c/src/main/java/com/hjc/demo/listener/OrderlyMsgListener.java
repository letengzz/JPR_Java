package com.hjc.demo.listener;

import com.hjc.demo.domain.Order;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import org.springframework.stereotype.Component;

/**
 * 创建一个对象消息的监听
 * 1.类上添加注解@Component和@RocketMQMessageListener
 * 2.实现RocketMQListener接口，注意泛型的使用
 * consumeMode 指定消费类型
 * CONCURRENTLY 并发消费
 * ORDERLY 顺序消费 messages orderly. one queue, one thread
 * @author hjc
 */
@Component
@RocketMQMessageListener(topic = "OrderlyTopicTest",
        consumerGroup = "orderly-consumer-group",
        consumeMode = ConsumeMode.ORDERLY)
public class OrderlyMsgListener implements RocketMQListener<Order> {

    /**
     * 消费消息的方法
     *
     * @param order
     */
    @Override
    public void onMessage(Order order) {
        System.out.println(order);
    }
}