package com.hjc.demo.consumer;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * 消费者
 * 设计去重表，对消息的唯一key添加唯一索引
 * 每次消费消息的时候，先插入数据库，如果成功则执行业务逻辑
 * 如果插入失败，则说明消息来过了，直接签收了
 *
 * @author hjc
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException, IOException {
        //创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("repeat-mysql-consumer-group");
        //设置nameServer地址
        consumer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //订阅一个主题来消费  *表示没有过滤参数 表示这个主题的任何消息
        consumer.subscribe("repeatTopic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //拿到key
                MessageExt messageExt = list.get(0);
                String keys = messageExt.getKeys();
                System.out.println(keys);
                //新增 成功或报错 修改 成功或失败或报错
                //插入数据库 因为做了唯一索引
                //原生操作
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocket?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false", "root", "123123");
                    PreparedStatement statement = connection.prepareStatement("insert into order_oper_log(`type`,`order_sn`,`user`) values (1,'" + keys + "','123')");
                    int i = statement.executeUpdate();
                } catch (SQLException e) {
                    if (e instanceof SQLIntegrityConstraintViolationException) {
                        //唯一索引冲突异常 说明消息已经来过了
                        System.out.println("该消息已经来过了");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    e.printStackTrace();
                }

                //处理业务逻辑
                //如果业务逻辑报错需要删除掉去重表记录 delete from order_oper_log where order_sn = keys
                System.out.println(new String(messageExt.getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 这个start一定要写在registerMessageListener下面
        consumer.start();
        // 由于是异步回调方式，挂起当前的jvm
        System.in.read();
    }
}
