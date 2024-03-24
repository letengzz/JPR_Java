package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import com.hjc.demo.domain.Order;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 生产者
 * @author hjc
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("orderly-send-producer");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动生产者
        producer.start();

        List<Order> orderList = Arrays.asList(
                new Order(1, 111, 59D, new Date(), "下订单"),
                new Order(2, 111, 59D, new Date(), "物流"),
                new Order(3, 111, 59D, new Date(), "签收"),
                new Order(4, 112, 89D, new Date(), "下订单"),
                new Order(5, 112, 89D, new Date(), "物流"),
                new Order(6, 112, 89D, new Date(), "拒收")

        );
        //循环集合开始发送
        //发送顺序消息  发送时要确保有序 并且要发到同一个队列下面去
        orderList.forEach(order -> {
            Message message = new Message("OrderTopicTest", order.toString().getBytes());
            try {
                // 送的时候 相同的订单号选择同一个队列
                producer.send(message, new MessageQueueSelector() { //队列选择器
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        // 当前主题有多少个队列
                        int queueNumber = list.size();
                        // 这个arg就是后面传入的 order.getOrderNumber()
                        Integer i = (Integer) o;
                        // 用这个值去%队列的个数得到一个队列
                        // 2 % 4 =2
                        // 3 % 4 =3
                        // 4 % 4 =0
                        // 5 % 4 =1
                        // 6 % 4 =2  周期性函数
                        int index = i % queueNumber;
                        // 返回选择的这个队列即可 ，那么相同的订单号 就会被放在相同的队列里 实现FIFO了
                        return list.get(index);
                    }
                },order.getOrderNumber() /*参数*/);
            }catch (Exception e){
                System.out.println("发送异常");
            }
        });

        //关闭实例
        producer.shutdown();
        System.out.println("发送完成");
    }
}
