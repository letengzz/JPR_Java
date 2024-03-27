package com.hjc.demo.consumer;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * 消费者
 * 设计去重表，对消息的唯一key添加唯一索引
 * 每次消费消息的时候，先插入数据库，如果成功则执行业务逻辑
 * 如果插入失败，则说明消息来过了，直接签收了
 *
 * @author hjc
 */
public class Consumer {
    public static BitMapBloomFilter bloomFilter = new BitMapBloomFilter(100);

    public static void main(String[] args) throws MQClientException, IOException {
        // 创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("repeat-hutool-consumer-group");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 设置nameServer地址
        consumer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        // 订阅一个主题来消费   表达式，默认是*
        consumer.subscribe("repeatTopic", "*");
        // 注册一个消费监听 MessageListenerConcurrently是并发消费
        // 默认是20个线程一起消费，可以参看 consumer.setConsumeThreadMax()
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                // 拿到消息的key
                MessageExt messageExt = msgs.get(0);
                String keys = messageExt.getKeys();
                // 判断是否存在布隆过滤器中
                if (bloomFilter.contains(keys)) {
                    // 直接返回了 不往下处理业务
                    System.out.println("该消息已经来过了");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                // 这个处理业务，然后放入过滤器中
                // do sth...
                System.out.println(new String(messageExt.getBody()));
                bloomFilter.add(keys);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.in.read();
    }
}
