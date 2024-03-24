package com.hjc.demo.consumer;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
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
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group");
        //设置nameServer地址
        consumer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        // 订阅一个主题来消费   表达式，默认是*,支持"tagA || tagB || tagC" 这样或者的写法 只要是符合任何一个标签都可以消费
        consumer.subscribe("KeyTopicTest", "tagA || tagB || tagC");
        // 注册一个消费监听 MessageListenerConcurrently是并发消费
        // 默认是20个线程一起消费，可以参看 consumer.setConsumeThreadMax()
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(Thread.currentThread().getName() + "----" + new String(list.get(0).getBody()));
                System.out.println(list.get(0).getTags());
                System.out.println(list.get(0).getKeys());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 这个start一定要写在registerMessageListener下面
        consumer.start();
        // 挂起当前的jvm
        System.in.read();
    }
}
