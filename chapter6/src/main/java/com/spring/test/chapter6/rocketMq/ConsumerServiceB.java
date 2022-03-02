package com.spring.test.chapter6.rocketMq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class ConsumerServiceB {

    private DefaultMQPushConsumer consumer = null;

    @PostConstruct
    public void initMQConsumer() {

        System.out.println("开启");

        consumer = new DefaultMQPushConsumer("defaultGroup4");
        consumer.setInstanceName("defaultInstance_4");// 设置实例名
        consumer.setNamesrvAddr("120.76.142.156:9876");
//        consumer.setMessageModel(MessageModel.BROADCASTING);// 广播模式
        try {
            consumer.subscribe("demo-topic", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        System.out.println("B Message Received: " + new String(msg.getBody()));
                        System.out.println("B Message Received Topic2: " + msg.getTags()+" "+msg.getTopic() + " "+msg.getKeys() +" " + msg.getMsgId());
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }



    }

    @PreDestroy
    public void shutDownConsumer() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }
}
