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
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("retry-consumer-group");
        //设置nameServer地址
        consumer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        // 订阅一个主题来消费   表达式，默认是*,支持"tagA || tagB || tagC" 这样或者的写法 只要是符合任何一个标签都可以消费
        consumer.subscribe("RetryTopicTest","tagA || tagB || tagC");
        // 设定重试次数 重试的次数一般 5次
        consumer.setMaxReconsumeTimes(5);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try {
                    // 这里执行消费的代码
                    System.out.println(Thread.currentThread().getName() + "----" + list);
                    // 这里制造一个错误
                    int i = 10 / 0;
                } catch (Exception e) {
                    // 出现问题 判断重试的次数
                    MessageExt messageExt = list.get(0);
                    // 获取重试的次数 失败一次消息中的失败次数会累加一次
                    int reconsumeTimes = messageExt.getReconsumeTimes();
                    System.out.println("reconsumeTimes = " + reconsumeTimes);
                    if (reconsumeTimes >= 3) {
                        // 则把消息确认了，可以将这条消息记录到日志或者数据库 通知人工处理
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    } else {
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.in.read();
    }
}
