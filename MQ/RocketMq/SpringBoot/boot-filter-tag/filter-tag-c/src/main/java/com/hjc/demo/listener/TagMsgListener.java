package com.hjc.demo.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 创建一个简单的标签消息的监听
 * 1.类上添加注解@Component和@RocketMQMessageListener
 *      selectorType = SelectorType.TAG,  指定使用tag过滤。(也可以使用sql92 需要在配置文件broker.conf中开启enbalePropertyFilter=true)
 *      selectorExpression = "tagA"     表达式，默认是*,支持"tag1 || tag2 || tag3"
 * 2.实现RocketMQListener接口，注意泛型的使用
 */
@Component
@RocketMQMessageListener(topic = "TagTopicTest",
        consumerGroup = "tag-consumer-group",
/*        selectorType = SelectorType.SQL92,
        selectorExpression = "a between 0 and 100"*/
        selectorType = SelectorType.TAG,
        selectorExpression = "tagA"
)
public class TagMsgListener implements RocketMQListener<String> {

    /**
     * 消费消息的方法
     *
     * @param message
     */
    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }
}