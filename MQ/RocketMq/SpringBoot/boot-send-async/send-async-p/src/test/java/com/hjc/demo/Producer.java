package com.hjc.demo;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Producer {

    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送异步消息
     * @throws IOException
     */
    @Test
    public void testAsyncSend() throws IOException {
        // 发送异步消息，发送完以后会有一个异步通知
        rocketMQTemplate.asyncSend("AsyncTopicTest", "发送一个异步消息", new SendCallback() {
            /**
             * 成功的回调
             * @param sendResult
             */
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功");
            }

            /**
             * 失败的回调
             * @param throwable
             */
            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送失败");
            }
        });
        // 测试一下异步的效果
        System.out.println("谁先执行");
        // 挂起jvm 不让方法结束
        System.in.read();
    }
}
