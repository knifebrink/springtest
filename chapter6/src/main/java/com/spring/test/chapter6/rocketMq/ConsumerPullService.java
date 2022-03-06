package com.spring.test.chapter6.rocketMq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
/**
 * pull模式，算了不管这个
 */
public class ConsumerPullService {
    private DefaultMQPullConsumer consumer = null;
    private MQConsumer mqConsumer;

//    @PostConstruct
    public void initMQConsumer() {
        consumer = new DefaultMQPullConsumer("defaultGroup2");
        consumer.setNamesrvAddr("120.76.142.156:9876");
        consumer.setInstanceName("consumer_pull_1");
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        try {
            consumer.createTopic("","default-topic",10);
            Set<MessageQueue>  messageQueues = consumer.fetchSubscribeMessageQueues("default-topic");
            log.info("pull：{}",messageQueues);
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
