package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;

/**
 * 生产者
 *
 * @author hjc
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException, IOException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("aysnc-send-producer");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动生产者
        producer.start();

        for (int i = 0; i < 10; i++) {
            //创建消息
            //第一个参数：主题的名字
            //第二个参数：消息内容
            Message msg = new Message("AsyncTopicTest", ("异步消息-" + i).getBytes());
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.err.println("发送失败:"+throwable.getMessage());
                }
            });
        }
        System.out.println("我先执行");
        //挂起jvm 因为回调是异步的 不然测试不出来
        System.in.read();
        //关闭实例
        producer.shutdown();
    }
}
