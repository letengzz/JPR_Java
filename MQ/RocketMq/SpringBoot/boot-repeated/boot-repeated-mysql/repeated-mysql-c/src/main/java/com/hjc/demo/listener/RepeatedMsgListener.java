package com.hjc.demo.listener;


import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * 创建一个简单消息的监听
 * 1. 类上添加注解@Component和@RocketMQMessageListener
 * @RocketMQMessageListener(topic = "SimpleTopicTest", consumerGroup = "simple-consumer-group")
 *      topic指定消费的主题，consumerGroup指定消费组,一个主题可以有多个消费者组,一个消息可以被多个不同的组的消费者都消费
 * 2. 实现RocketMQListener接口，注意泛型的使用，可以为具体的类型，如果想拿到消息的其他参数可以写成MessageExt
 */
@Component
@RocketMQMessageListener(topic = "repeatTopic",
        consumerGroup = "repeat-consumer-group")
public class RepeatedMsgListener implements RocketMQListener<MessageExt> {
    /**
     * 消费消息的方法
     * 如果泛型指定了固定的类型 那么消息体就是参数
     * MessageExt 类型是消息的所有内容
     * 如果没有报错 就签收了
     * 如果报错了 就是拒收 就会重试
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        //拿到key
        String keys = messageExt.getKeys();
        //新增 成功或报错 修改 成功或失败或报错
        //插入数据库 因为做了唯一索引
        //原生操作
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocket?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false", "root", "123123");
            PreparedStatement statement = connection.prepareStatement("insert into order_oper_log(`type`,`order_sn`,`user`) values (1,'" + keys + "','123')");
            int i = statement.executeUpdate();
            //处理业务逻辑
            //如果业务逻辑报错需要删除掉去重表记录
            System.out.println(new String(messageExt.getBody()));
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                //唯一索引冲突异常 说明消息已经来过了
                System.out.println("该消息已经来过了");
            }
//            e.printStackTrace();
        }
    }
}
