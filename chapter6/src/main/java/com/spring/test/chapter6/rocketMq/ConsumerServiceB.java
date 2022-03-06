package com.spring.test.chapter6.rocketMq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.QueryResult;
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
@Slf4j
public class ConsumerServiceB {

    private DefaultMQPushConsumer consumer = null;
    private DefaultMQPushConsumer consumer3 = null;

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
                        System.out.println("B Received: " + new String(msg.getBody()));
                        log.info("thread: {}",Thread.currentThread());
//                        System.out.println("B Message Received Topic2: " + msg.getTags()+" "+msg.getTopic() + " "+msg.getKeys() +" " + msg.getMsgId());
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

    /**
     * 测试多个
     */
    @PostConstruct
    private void fun(){
        consumer3 = new DefaultMQPushConsumer("defaultGroup66");
        consumer3.setInstanceName("defaultInstance_5");// 设置实例名
        consumer3.setNamesrvAddr("120.76.142.156:9876");

        try {

            consumer3.subscribe("demo-topic", "*");

            log.info("开始阻塞查询");
            QueryResult queryResult = consumer3.queryMessage("demo-topic","",10,0,10);
            log.info("阻塞查询：{}",queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
