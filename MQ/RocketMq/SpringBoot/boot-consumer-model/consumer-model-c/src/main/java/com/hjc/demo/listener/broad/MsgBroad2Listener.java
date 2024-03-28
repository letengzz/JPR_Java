package com.hjc.demo.listener.broad;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * 创建一个简单消息的监听
 * 1. 类上添加注解@Component和@RocketMQMessageListener
 * RocketMQMessageListener(topic = "SimpleTopicTest", consumerGroup = "simple-consumer-group")
 * topic指定消费的主题，consumerGroup指定消费组,一个主题可以有多个消费者组,一个消息可以被多个不同的组的消费者都消费
 * 2. 实现RocketMQListener接口，注意泛型的使用，可以为具体的类型，如果想拿到消息的其他参数可以写成MessageExt
 */
@Component
@RocketMQMessageListener(topic = "ModeBroadTopicTest",
        consumerGroup = "broad-consumer-group",
        messageModel = MessageModel.BROADCASTING
        /* messageModel  指定消息消费的模式
         *   CLUSTERING 为负载均衡模式
         *   BROADCASTING 为广播模式 */
)
public class MsgBroad2Listener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    /**
     * 消费消息的方法
     * 如果泛型指定了固定的类型 那么消息体就是参数
     * MessageExt 类型是消息的所有内容
     * 如果没有报错 就签收了
     * 如果报错了 就是拒收 就会重试
     *
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println("Msg2---" + new String(messageExt.getBody()));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        //设置当前实例的名称
        consumer.setInstanceName("MsgBroad2Listener");
    }
}
