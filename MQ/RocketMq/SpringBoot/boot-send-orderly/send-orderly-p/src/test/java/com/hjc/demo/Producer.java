package com.hjc.demo;

import com.alibaba.fastjson.JSON;
import com.hjc.demo.domain.Order;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class Producer {


    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送顺序消息
     */
    @Test
    public void testOrderlySend(){
        List<Order> orders = Arrays.asList(
                new Order(UUID.randomUUID().toString().substring(0, 5), "张三的下订单", null, null, null, 1),
                new Order(UUID.randomUUID().toString().substring(0, 5), "张三的发短信", null, null, null, 1),
                new Order(UUID.randomUUID().toString().substring(0, 5), "张三的物流", null, null, null, 1),
                new Order(UUID.randomUUID().toString().substring(0, 5), "张三的签收", null, null, null, 1),
                new Order(UUID.randomUUID().toString().substring(0, 5), "李四的下订单", null, null, null, 2),
                new Order(UUID.randomUUID().toString().substring(0, 5), "李四的发短信", null, null, null, 2),
                new Order(UUID.randomUUID().toString().substring(0, 5), "李四的物流", null, null, null, 2),
                new Order(UUID.randomUUID().toString().substring(0, 5), "李四的签收", null, null, null, 2)
        );
        orders.forEach(order -> {
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("OrderlyTopicTest", order, String.valueOf(order.getSeq()));
            System.out.println(sendResult);
        });
    }
}
