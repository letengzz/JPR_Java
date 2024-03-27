package com.hjc.demo.product;

import com.hjc.demo.constant.MqConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.util.Date;

public class Producer {
    /**
     * TransactionalMessageCheckService的检测频率默认1分钟，可通过在broker.conf文件中设置transactionCheckInterval的值来改变默认值，单位为毫秒。
     * 从broker配置文件中获取transactionTimeOut参数值。
     * 从broker配置文件中获取transactionCheckMax参数值，表示事务的最大检测次数，如果超过检测次数，消息会默认为丢弃，即回滚消息。
     *
     */
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException, IOException {
        //创建一个事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("transaction-producer-group");
        producer.setNamesrvAddr(MqConstant.NAME_SRV_ADDR_LINUX);
        //设置事务消息监听器
        producer.setTransactionListener(new TransactionListener() {
            //执行本地业务方法
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                // 这个可以使用try catch对业务代码进行性包裹
                // COMMIT_MESSAGE 表示允许消费者消费该消息
                // ROLLBACK_MESSAGE 表示该消息将被删除，不允许消费
                // UNKNOW表示需要MQ回查才能确定状态 那么过一会 代码会走下面的checkLocalTransaction(msg)方法

                // TODO 执行业务代码（插入订单数据库表）
                // int i = orderDatabaseService.insert(....)
                // TODO 提交或回滚本地事务(如果用spring事务注解，这些都不需要手工去操作)

                // 模拟一个处理结果
//                int index = 5;
                //模拟返回事务状态
                switch ((int)o) {
                    case 3:
                        System.out.printf("本地事务回滚，回滚消息，id:%s%n", message.getKeys());
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    case 5:
                    case 8:
                        return LocalTransactionState.UNKNOW;
                    default:
                        System.out.println("事务提交，消息正常处理");
                        return LocalTransactionState.COMMIT_MESSAGE;
                }
            }

            // 这里是回查方法 回查不是再次执行业务操作，而是确认上面的操作是否有结果
            // 默认是1min回查 默认回查15次 超过次数则丢弃打印日志 可以通过参数设置
            // transactionTimeOut 超时时间
            // transactionCheckMax 最大回查次数
            // transactionCheckInterval 回查间隔时间单位毫秒
            // 触发条件:
            //   1.当上面执行本地事务返回结果UNKNOW时,或者下面的回查方法也返回UNKNOW时 会触发回查
            //   2.当上面操作超过20s没有做出一个结果，也就是超时或者卡住了，也会进行回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                // 根据业务，正确处理： 订单场景，只要数据库有了这条记录，消息应该被commit
                String transactionId = messageExt.getTransactionId();
                String key = messageExt.getKeys();
                System.out.printf("回查事务状态 key:%-5s msgId:%-10s transactionId:%-10s %n", key, messageExt.getMsgId(), transactionId);

                if ("id_5".equals(key)) { // 刚刚测试的10条消息， 把id_5这条消息提交，其他的全部回滚。
                    System.out.printf("回查到本地事务已提交，提交消息，id:%s%n", messageExt.getKeys());
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else {
                    System.out.printf("未查到本地事务状态，回滚消息，id:%s%n", messageExt.getKeys());
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }
        });
        producer.start();
        for (int i = 0; i < 10; i++) {
            String content = "Hello transaction message " + i;
            Message message = new Message("TransactionTopicTest", "TagA", "id_" + i, content.getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 发送消息(发送一条新订单生成的通知)
            SendResult result = producer.sendMessageInTransaction(message, i);

            System.out.printf("发送结果：%s%n", result);
        }
        System.out.println(new Date());
        System.in.read();
    }
}
