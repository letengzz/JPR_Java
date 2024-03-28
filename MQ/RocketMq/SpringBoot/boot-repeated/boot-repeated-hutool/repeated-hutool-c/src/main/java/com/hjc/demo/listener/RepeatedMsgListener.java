package com.hjc.demo.listener;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 创建一个简单消息的监听
 * 1. 类上添加注解@Component和@RocketMQMessageListener
 * @RocketMQMessageListener(topic = "SimpleTopicTest", consumerGroup = "simple-consumer-group")
 *      topic指定消费的主题，consumerGroup指定消费组,一个主题可以有多个消费者组,一个消息可以被多个不同的组的消费者都消费
 * 2. 实现RocketMQListener接口，注意泛型的使用，可以为具体的类型，如果想拿到消息的其他参数可以写成MessageExt
 */
@Component
@RocketMQMessageListener(topic = "repeatTopic",
        consumerGroup = "repeat-consumer-group")
public class RepeatedMsgListener implements RocketMQListener<MessageExt> {
    public static BitMapBloomFilter bloomFilter = new BitMapBloomFilter(100);
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
        // 拿到消息的key
        String keys = messageExt.getKeys();
        // 判断是否存在布隆过滤器中
        if (!bloomFilter.contains(keys)) {
            // 这个处理业务，然后放入过滤器中
            // do sth...
            System.out.println(new String(messageExt.getBody()));
            bloomFilter.add(keys);
        }else {
            // 直接返回了 不往下处理业务
            System.out.println("该消息已经来过了");
        }
    }
}
