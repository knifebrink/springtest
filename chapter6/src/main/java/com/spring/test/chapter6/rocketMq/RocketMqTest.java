package com.spring.test.chapter6.rocketMq;


import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 换成集中的测试，原来的跳来跳去搞得我烦
 * 测试结果：无论延时多少，consumer2总会漏掉一个消息，神奇，而且是刚好一个
 * <p>
 * 有时候会莫名奇妙多很多的消息。。。幂等性吗
 *
 * 可以多看看官网，中文的描述确实是舒服的。
 * https://blog.csdn.net/qq_21383435/article/details/101113808
 */
public class RocketMqTest {
    private static final String NAME_SRV_ADDR = "120.76.142.156:9876";
    private static DefaultMQPushConsumer consumer = null;
    private static DefaultMQProducer producer = null;
    private static final String TOPIC_1 = "demo-topic";
    private static Logger log = LoggerFactory.getLogger("fch111");
    private static final String CONSUMER_GROUP_NAME_1 = "defaultGroup1";
    private static DefaultMQPushConsumer consumer2;

    public static void main(String[] args) {
        try {
            fun();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date());
    }

    public static void fun() throws InterruptedException {
//        initConsumer();
//        initConsumer2();
        initConsumer3();
        initProducer();

        Message msg = new Message(TOPIC_1, "demo-tag", "", (getTime() + "这是发送的信息1").getBytes());
        Message msg2 = new Message(TOPIC_1, "demo-tag", "", (getTime() + "这是发送的信息2").getBytes());
        Message msg3 = new Message(TOPIC_1, "demo-tag", "", (getTime() + "这是发送的信息3").getBytes());
        Message msg4 = new Message(TOPIC_1, "demo-tag", "", (getTime() + "这是发送的信息4").getBytes());
        Message msg5 = new Message(TOPIC_1, "demo-tag", "", (getTime() + "这是发送的信息5").getBytes());
        try {
            producer.send(msg);
            producer.send(msg2);
            producer.send(msg3);
            producer.send(msg4);
            producer.send(msg5);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread.sleep(3000);

        Thread.sleep(40000);


        Thread.sleep(40000000);
        consumer.shutdown();
        consumer2.shutdown();
        producer.shutdown();
    }

    public static void initProducer() {
        producer = new DefaultMQProducer("defaultProducerGroup");
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        producer.setRetryTimesWhenSendFailed(3);
        producer.setSendMsgTimeout(30000);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static boolean send(String topic, String tags, String content) {
        Message msg = new Message(topic, tags, "", content.getBytes());
        try {
            producer.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void initConsumer() {
        String instanceName = "consumer_1";
        consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(CONSUMER_GROUP_NAME_1);
        consumer.setNamesrvAddr(NAME_SRV_ADDR);
        consumer.setInstanceName(instanceName);
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        try {
            consumer.subscribe(TOPIC_1, "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {

                        System.out.println("consumer1: " + new String(msg.getBody()));
//                        log.info("thread: {}",Thread.currentThread());
//                        System.out.println("Received Topic: " + msg.getTags()+" "+msg.getTopic() + " "+msg.getKeys());

                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }


    public static void initConsumer2() {
        String instanceName = "consumer_24";
        consumer2 = new DefaultMQPushConsumer(CONSUMER_GROUP_NAME_1);
        consumer2.setNamesrvAddr(NAME_SRV_ADDR);
        consumer2.setInstanceName(instanceName);
//        consumer2.setMessageModel(MessageModel.BROADCASTING);
        consumer2.setConsumeThreadMax(1);
        consumer2.setConsumeThreadMin(1);
        try {
            consumer2.subscribe(TOPIC_1, "*");
            consumer2.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {

                        System.out.println("consumer2: " + new String(msg.getBody()));
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        log.info("thread: {}",Thread.currentThread());
//                        System.out.println("Received Topic: " + msg.getTags()+" "+msg.getTopic() + " "+msg.getKeys());

                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer2.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();

    public static void initConsumer3() {
        offsetTable.clear();
        DefaultMQPullConsumer consumer3 = new DefaultMQPullConsumer("pullConsumer");
        consumer3.setNamesrvAddr(NAME_SRV_ADDR);
        try {
            consumer3.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 绑定主题
                    Set<MessageQueue> mqs = consumer3.fetchSubscribeMessageQueues(TOPIC_1);
                    // 对不同消息队列进行取消息
                    for (MessageQueue mq : mqs) {
                        long offset = 0;
                        System.out.println("当前获取的消息的归属队列是: " + mq.getQueueId());
                        for (; ; ) {
                            // 循环重复获取消息
                            // 应该是有个借口可以直接获取消息的队列点的
                            PullResultExt pullResult = (PullResultExt) consumer3.pullBlockIfNotFound(mq, null,
                                    offset, 32);

                            if(offset == pullResult.getNextBeginOffset()){
                                break;
                            }
                            offset = pullResult.getNextBeginOffset();
                            System.out.println("下次：" + pullResult.getNextBeginOffset());
                            switch (pullResult.getPullStatus()) {

                                case FOUND:

                                    List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                                    for (MessageExt m : messageExtList) {
                                        System.out.println("consumer3_pull收到了消息:" + new String(m.getBody()));
                                    }
                                    break;

                                case NO_MATCHED_MSG:
                                    System.out.println("consumer3_pull NO_MATCHED_MSG");
                                    break;

                                case NO_NEW_MSG:
                                    System.out.println("consumer3_pull NO_NEW_MSG");
                                    break;

                                case OFFSET_ILLEGAL:
                                    System.out.println("consumer3_pull OFFSET_ILLEGAL");
                                    break;

                                default:
                                    break;
                            }

                        }
                    }
                    // }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }


}
