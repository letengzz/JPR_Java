package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.Date;
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
     * 测试发送数组消息
     *
     * @throws Exception
     */
    @Test
    public void testListMsg() throws Exception {
        List<String> list = Arrays.asList("A", "B", "C");
        // 往ListTopicTest主题发送一个订单对象
        rocketMQTemplate.syncSend("ListTopicTest", list);
    }
}
