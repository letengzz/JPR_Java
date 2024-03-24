package com.hjc.demo.consumer;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;

/**
 *  消费者
 * @author hjc
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException, IOException {
        //创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderly-consumer-group");
        //设置nameServer地址
        consumer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //订阅一个主题来消费  *表示没有过滤参数 表示这个主题的任何消息
        consumer.subscribe("OrderTopicTest","*");
        // MessageListenerConcurrently 并发模式 多线程的  重试16次
        // MessageListenerOrderly 顺序模式 单线程的   无限重试Integer.Max_Value
        // 注册一个消费监听 MessageListenerOrderly 是顺序消费 单线程消费
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println("线程id:" + Thread.currentThread().getId());
                System.out.println(new String(list.get(0).getBody()));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        // 这个start一定要写在registerMessageListener下面
        consumer.start();
        // 挂起当前的jvm
        System.in.read();
    }
}
