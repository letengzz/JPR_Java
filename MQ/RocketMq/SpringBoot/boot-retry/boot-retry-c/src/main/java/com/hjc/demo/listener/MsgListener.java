package com.hjc.demo.listener;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 创建一个简单消息的监听
 * 1. 类上添加注解@Component和@RocketMQMessageListener
 * @RocketMQMessageListener(topic = "SimpleTopicTest", consumerGroup = "simple-consumer-group")
 *      topic指定消费的主题，consumerGroup指定消费组,一个主题可以有多个消费者组,一个消息可以被多个不同的组的消费者都消费
 * 2. 实现RocketMQListener接口，注意泛型的使用，可以为具体的类型，如果想拿到消息的其他参数可以写成MessageExt
 */
@Component
@RocketMQMessageListener(topic = "DeadTopicTest",
        consumerGroup = "dead-consumer-group",
        selectorExpression = "tagA || tagB || tagC"
)
public class MsgListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    /**
     * 消费消息的方法
     * 如果泛型指定了固定的类型 那么消息体就是参数
     * MessageExt 类型是消息的所有内容
     * 如果没有报错 就签收了
     * 如果报错了 就是拒收 就会重试
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println(new Date());
        System.out.println(new String(messageExt.getBody()));
        // 获取重试的次数 失败一次消息中的失败次数会累加一次
        System.out.println("消息处理次数的处理次数:"+messageExt.getReconsumeTimes());
        // 模拟报错
        throw new RuntimeException("消费失败");
    }
    /**
     * 对消费者客户端的一些配置
     * 重写prepareStart方法
     * @param defaultMQPushConsumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        // 设定重试次数 重试的次数一般 5次
        defaultMQPushConsumer.setMaxReconsumeTimes(5);
    }
}
