package com.hjc.demo;

import com.hjc.demo.domain.Order;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class Producer {

    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 测试发送对象消息
     *
     * @throws Exception
     */
    @Test
    public void testObjectMsg() throws Exception {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderName("我的订单");
        order.setPrice(998D);
        order.setCreateTime(new Date());
        order.setDesc("加急配送");
        // 往ObjectTopicTest主题发送一个订单对象
        rocketMQTemplate.syncSend("ObjectTopicTest", order);
    }
}
