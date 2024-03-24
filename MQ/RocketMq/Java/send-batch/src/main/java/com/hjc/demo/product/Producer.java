package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Arrays;
import java.util.List;

/**
 * 生产者
 *
 * @author hjc
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("batch-producer-group");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动生产者
        producer.start();
        //创建消息
        //第一个参数：主题的名字
        //第二个参数：消息内容
        List<Message> msgs = Arrays.asList(
                new Message("BatchTopicTest", "我是一组消息的A消息".getBytes()),
                new Message("BatchTopicTest", "我是一组消息的B消息".getBytes()),
                new Message("BatchTopicTest", "我是一组消息的C消息".getBytes())
        );

        SendResult send = producer.send(msgs);
        System.out.println("send = " + send);
        //关闭实例
        producer.shutdown();
    }
}
