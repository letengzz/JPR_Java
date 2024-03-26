package com.hjc.demo.consumer;

import com.hjc.demo.constant.MqConstant;
import io.netty.util.CharsetUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;

/**
 * @author hjc
 */
public class SplitterConsumer {
    public static void main(String[] args) throws MQClientException, IOException {
        //创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("batch-consumer-group");
        //设置nameServer地址
        consumer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //订阅一个主题来消费  *表示没有过滤参数 表示这个主题的任何消息
        consumer.subscribe("BatchTopicTest","*");
        //注册一个消费监听 MessageListenerConcurrently 是多线程消费，默认20个线程
        // 可以参看consumer.setConsumeThreadMax()
//        consumer.setMessageModel(MessageModel.CLUSTERING);

        //设置每次消息拉取的时间间隔 单位 毫秒
        consumer.setPullInterval(1000);
        //最小消费线程池数
        consumer.setConsumeThreadMin(1);
        //最大消费线程池数
        consumer.setConsumeThreadMax(10);
        //设置消费者单次批量消费的消息数目上限    默认1
        consumer.setConsumeMessageBatchMaxSize(10);
        //设置每个队列每次拉取的最大消费数
        consumer.setPullBatchSize(16);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println("批量消息，总个数:"+list.size());
                list.forEach(message->{
                    System.out.println("批量消息 body:"+new String(message.getBody(), CharsetUtil.UTF_8));
                });
                // 返回消费的状态 如果是SUCCESS 则成功，若为LATER则该条消息会被重回队列，重新被投递
                // 重试的时间为messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                // 也就是第一次1s 第二次5s 第三次10s  ....  如果重试了18次 那么这个消息就会被终止发送给消费者
                // 返回状态：
                // SUCCESS：成功 消息会从mq出队
                // LATER：失败(报错、null) 消息会重新回到队列 过一会重新投递出来 给当前消费者或者其他消费者消费的
                return ConsumeOrderlyStatus.SUCCESS;
                // return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        // 这个start一定要写在registerMessageListener下面
        consumer.start();
        // 由于是异步回调方式，挂起当前的jvm
        System.in.read();

    }
}
