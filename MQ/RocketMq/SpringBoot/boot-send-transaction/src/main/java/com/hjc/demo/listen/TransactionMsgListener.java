package com.hjc.demo.listen;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 事务消息的监听与回查
 * 类上添加注解@RocketMQTransactionListener 表示这个类是本地事务消息的监听类
 * 实现RocketMQLocalTransactionListener接口
 * 两个方法为执行本地事务，与回查本地事务
 */
@Component
@RocketMQTransactionListener(corePoolSize = 4,maximumPoolSize = 8)
public class TransactionMsgListener implements RocketMQLocalTransactionListener {
    /**
     * 执行本地事务，这里可以执行一些业务
     * 比如操作数据库，操作成功就return RocketMQLocalTransactionState.COMMIT;
     * 可以使用try catch来控制成功或者失败;
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 拿到消息参数
        System.out.println(arg);
        // 拿到消息头
        System.out.println(msg.getHeaders());
        // 返回状态COMMIT,UNKNOWN
        return RocketMQLocalTransactionState.UNKNOWN;
    }
    /**
     * 回查本地事务，只有上面的执行方法返回UNKNOWN时，才执行下面的方法 默认是1min回查
     * 此方法为回查方法，执行需要等待一会
     * xxx.isSuccess()  这里可以执行一些检查的方法
     * 如果返回COMMIT，那么本地事务就算是提交成功了，消息就会被消费者看到
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println(msg);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
