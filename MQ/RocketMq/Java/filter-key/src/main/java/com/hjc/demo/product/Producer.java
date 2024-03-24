package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.UUID;

/**
 * 生产者
 * @author hjc
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("key-producer-group");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动生产者
        producer.start();

        //key 要确保唯一 为了查阅和去重
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        Message message = new Message("KeyTopicTest", "tagA", uuid, "我是一个带标记和key的消息A".getBytes());
        // 发送两个消息
        SendResult send1 = producer.send(message);
        System.out.println("send1 = " + send1);
        // 关闭实例
        producer.shutdown();
    }
}
