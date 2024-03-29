package com.hjc.demo.listener;

import com.hjc.demo.domain.Order;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 创建一个对象消息的监听
 * 1.类上添加注解@Component和@RocketMQMessageListener
 * 2.实现RocketMQListener接口，注意泛型的使用
 */
@Component
@RocketMQMessageListener(topic = "ObjectTopicTest",
        consumerGroup = "object-consumer-group")
public class ObjectMsgListener implements RocketMQListener<Order> {
    /**
     * 消费消息的方法
     * 如果泛型指定了固定的类型 那么消息体就是参数
     * MessageExt 类型是消息的所有内容
     * 如果没有报错 就签收了
     * 如果报错了 就是拒收 就会重试
     * @param order
     */
    @Override
    public void onMessage(Order order) {
        System.out.println(order);
    }
}
