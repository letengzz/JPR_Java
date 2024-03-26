package com.hjc.demo.listener;

import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 创建一个简单消息的监听
 * 1. 类上添加注解@Component和@RocketMQMessageListener
 * RocketMQMessageListener(topic = "SimpleTopicTest", consumerGroup = "simple-consumer-group")
 *      topic指定消费的主题，consumerGroup指定消费组,一个主题可以有多个消费者组,一个消息可以被多个不同的组的消费者都消费
 * 2. 实现RocketMQListener接口，注意泛型的使用，可以为具体的类型，如果想拿到消息的其他参数可以写成MessageExt
 * @author hjc
 */
@Component
@RocketMQMessageListener(topic = "BatchSplitterTopicTest",consumerGroup = "batch-consumer-group",consumeMode = ConsumeMode.ORDERLY)
public class ListSplitterListener implements RocketMQListener<List<MessageExt>>,RocketMQPushConsumerLifecycleListener {
    /**
     * 消费消息的方法
     * 如果泛型指定了固定的类型 那么消息体就是参数
     * MessageExt 类型是消息的所有内容
     * 如果没有报错 就签收了
     * 如果报错了 就是拒收 就会重试
     * @param messages
     */
    @Override
    public void onMessage(List<MessageExt> messages) {
        System.out.println("批量消息，总个数:"+messages.size());
        messages.forEach(message->{
            System.out.println("批量消息 body:"+new String(message.getBody(), CharsetUtil.UTF_8));
        });
    }

    /**
     * 对消费者客户端的一些配置
     * 重写prepareStart方法
     * @param defaultMQPushConsumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        //设置每次消息拉取的时间间隔 单位 毫秒
        defaultMQPushConsumer.setPullInterval(1000);
        //最小消费线程池数
        defaultMQPushConsumer.setConsumeThreadMin(1);
        //最大消费线程池数
        defaultMQPushConsumer.setConsumeThreadMax(10);
        //设置消费者单次批量消费的消息数目上限    默认1
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(10);
        //设置每个队列每次拉取的最大消费数
        defaultMQPushConsumer.setPullBatchSize(16);
    }
}