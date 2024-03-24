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
        //创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("oneway-send-producer");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动实例
        producer.start();

        Message message = new Message("OnewayTopicTest", "单向消息".getBytes());
        //发送单向消息
        producer.sendOneway(message);
        System.out.println("消息发送成功");
        //关闭实例
        producer.shutdown();
    }
}
