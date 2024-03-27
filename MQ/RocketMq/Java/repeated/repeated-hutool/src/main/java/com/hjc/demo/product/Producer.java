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
import java.util.UUID;

/**
 * 生产者
 *
 * @author hjc
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("repeat-hutool-producer-group");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动生产者
        producer.start();
        //生成唯一id
        String key = UUID.randomUUID().toString();
        //创建消息
        //第一个参数：主题的名字
        //第二个参数：消息内容
        //发两个同样的消息模拟重复消费
        Message m1 = new Message("repeatTopic", null, key, "扣减库存-1".getBytes());
        Message m1Repeat = new Message("repeatTopic", null, key, "扣减库存-1".getBytes());

        producer.send(m1);
        producer.send(m1Repeat);
        System.out.println("发送消息成功");
        //关闭实例
        producer.shutdown();
    }
}
