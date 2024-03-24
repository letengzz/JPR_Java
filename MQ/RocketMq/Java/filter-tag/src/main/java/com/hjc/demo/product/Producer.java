package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 生产者
 * @author hjc
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("tag-producer-group");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动实例
        producer.start();

        Message message1 = new Message("TagTopicTest", "tagA", "我是一个带标记的消息A".getBytes());
        Message message2 = new Message("TagTopicTest", "tagB", "我是一个带标记的消息B".getBytes());
        SendResult send1 = producer.send(message1);
        SendResult send2 = producer.send(message2);
        System.out.println("send1 = " + send1);
        System.out.println("send2 = " + send2);
        // 关闭实例
        producer.shutdown();
    }
}
