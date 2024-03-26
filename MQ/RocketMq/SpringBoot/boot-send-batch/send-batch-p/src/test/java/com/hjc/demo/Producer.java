package com.hjc.demo;

import com.hjc.demo.splitter.ListSplitter;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;


import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class Producer {


    /**
     * 注入rocketMQTemplate，使用它来操作mq
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送批量消息
     */
    @Test
    public void testBatchSend(){
        //创建消息
        List<org.springframework.messaging.Message> msgs = new ArrayList<>();
        for(int i=0;i<10;i++){
            msgs.add(MessageBuilder.withPayload("批量消息"+(i+1)).build());
        }
        // 发送批量消息
        SendResult sendResult = rocketMQTemplate.syncSend("BatchTopicTest", msgs);
        System.out.println("sendResult = " + sendResult);
    }

    /**
     * 发送批量消息(大于4MB)
     * 发送批量消息，最主要的区别是在发送消息的send方法入参一个List。
     */
    @Test
    public void testBatchSendSplitter(){
        List<Message> messageList = new ArrayList<>();
        for(int i=0;i<1000;i++){
            //添加内容
            byte[] bytes = (("批量消息"+i).getBytes(CharsetUtil.UTF_8));
            messageList.add(new Message("BatchSplitterTopicTest","message-tag-batch",bytes));
        }

        //切割消息
        //把大的消息分裂传给你若干个小的消息
        ListSplitter splitter = new ListSplitter(messageList);
        while(splitter.hasNext()){
            List<Message> listItem = splitter.next();
            //发送消息
            //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
            //参数二：消息内容
            SendResult sendResult = rocketMQTemplate.syncSend("BatchSplitterTopicTest",listItem,6000);
            System.out.println(sendResult);
        }
    }
}
