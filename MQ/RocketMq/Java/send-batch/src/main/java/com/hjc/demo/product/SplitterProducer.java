package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import com.hjc.demo.splitter.ListSplitter;
import io.netty.util.CharsetUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

public class SplitterProducer {
    /**
     * 发送批量消息(大于4MB)
     * 发送批量消息，最主要的区别是在发送消息的send方法入参一个List。
     */
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("batch-producer-group");
        //设置nameServer地址
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //启动生产者
        producer.start();

        List<Message> messageList = new ArrayList<>();
        for(int i=0;i<1000;i++){
            //添加内容
            byte[] bytes = (("批量消息"+i).getBytes(CharsetUtil.UTF_8));
            messageList.add(new Message("BatchTopicTest","message-tag-batch",bytes));
        }

        //切割消息
        //把大的消息分裂传给你若干个小的消息
        ListSplitter splitter = new ListSplitter(messageList);
        while(splitter.hasNext()){
            List<Message> listItem = splitter.next();
            //发送消息
            //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
            //参数二：消息内容
            SendResult sendResult = producer.send(listItem,6000);
            System.out.println(sendResult);
        }
        //关闭实例
        producer.shutdown();
    }
}
