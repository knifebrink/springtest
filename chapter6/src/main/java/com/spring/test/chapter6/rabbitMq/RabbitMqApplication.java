package com.spring.test.chapter6.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author brink
 * 2021/9/18 9:42
 * rabbitMq 测试
 * 消息队列框架。
 * 可通过localhost:15672打开管理页面。
 *
 * 只需要定义一个队列即可，还是spring提供的接口
 * 特性挺有意思的。
 * 中间会有个队列一直存着，如果没有取出，会一直存在，直到取出或设置了超时
 * 会有 交换机、路由键、队列名 。 然后根据交换机+路由键发送，也可以根据队列名发送（但实际上经过默认交换机？）。测试是这样
 * 感觉还有其他的特定，但，下次再搞了。
 *
 * 坑：即使停止了程序，rabbitMQ也没停止，所以，有时候队列中有接收不了类型的数据，会一直报错，取出就好。
 * 参考连接：http://www.macrozheng.com/#/architect/mall_arch_09
 * https://blog.csdn.net/kavito/article/details/91403659
 */
@SpringBootApplication
public class RabbitMqApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(RabbitMqApplication.class);
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(RabbitMqApplication.class,args);

        try {
            RabbitMqApplication rabbitMqApplication = applicationContext.getBean(RabbitMqApplication.class);
//            rabbitMqApplication.sendEasyMessage();
//            rabbitMqApplication.sendEasyMessage2();
//            Thread.sleep(5000);
            rabbitMqApplication.sendDelayMessage(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 根据队列名称发送消息
     * 使用监听器的方式进行接收
     */
    public void sendEasyMessage(){
        amqpTemplate.convertAndSend(RabbitMqConfig.EASY_QUEUE_NAME,"这是一个easyQueue测试");// 发送String
        amqpTemplate.convertAndSend(RabbitMqConfig.EASY_QUEUE_NAME,33L);// 发送Long类型
        amqpTemplate.convertAndSend(RabbitMqConfig.EASY_QUEUE_NAME,new RabbitMqUser("testId","testName"));
        LOGGER.info("easyQueue：发送{}","这是一个easyQueue测试");
//        Object o  = amqpTemplate.receiveAndConvert(RabbitMqConfig.EASY_QUEUE_NAME);
//        amqpTemplate.receive(RabbitMqConfig.EASY_QUEUE_NAME);

//        amqpTemplate.send

        LOGGER.info("sendEasyMessage send end");
    }

    // 使用原生的方法接收
    public void sendEasyMessage2() throws InterruptedException {
        LOGGER.info("sendEasyMessage2 send start 需要5秒完全执行");
        new Thread(){
            @Override
            public void run() {
                try {
                    // 延迟接收，并且自己解码
                    Message message = amqpTemplate.receive(RabbitMqConfig.EASY_QUEUE_NAME_2,2000);
                    // 自己接收，然后转码
                    LOGGER.info("sendEasyMessage2：{}  Message:{}",""+deserializeToJavaBaseType(message.getBody()),message.toString());

                    // 接着继续接收，但还没发送
                    Object o = amqpTemplate.receiveAndConvert(RabbitMqConfig.EASY_QUEUE_NAME_2);
                    LOGGER.info("sendEasyMessage2： 第二次接收：{} ",o);

                    Thread.sleep(3000);
                    Object o2 = amqpTemplate.receiveAndConvert(RabbitMqConfig.EASY_QUEUE_NAME_2);
                    LOGGER.info("sendEasyMessage2：{} 最后一次接收 ",o2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Thread.sleep(1000);
        // 发送float，因为EasyReceiver没有成功接收Float，所以报错。而且它优先级较高？
        amqpTemplate.convertAndSend(RabbitMqConfig.EASY_QUEUE_NAME_2,0.5f);

        // 延迟1500发送
        Thread.sleep(1500);
        amqpTemplate.convertAndSend(RabbitMqConfig.EASY_QUEUE_NAME_2,"这是最后一个发送");
    }

    /**
     * 发送另外另个队列的
     */
    public void sendDelayMessage(final long delayTimes){
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_TTL_EXCHANGE,
                RabbitMqConfig.ORDER_TTL_ROUTE_KEY, "发送给TTL队列，但它不在（被我注释掉了^_^）", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });
//        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_TTL_EXCHANGE,RabbitMqConfig.ORDER_TTL_ROUTE_KEY,"发送个第二个");
//        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_TTL_QUEUE_NAME,5L); // 单独根据队列名可以发送
//        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_TTL_ROUTE_KEY,6L); // 单独根据routeKey不能发送
        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_QUEUE_NAME,5L); // 发送给第一个
        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_EXCHANGE,RabbitMqConfig.ORDER_ROUTE_KEY,"发送个第一个");
        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_QUEUE_NAME,"通过队列名称发送");
        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_ROUTE_KEY,"只通过路由发送");// 发不过去，需交换机
        // 默认的exchange为""。
        LOGGER.info("send delay message end");
    }

    private String deserializeToJavaBaseType(byte[] body){
        Set<String> ALLOWED_LIST_PATTERNS =
                new LinkedHashSet<>(Arrays.asList("java.util.*", "java.lang.*"));
        try {
            return SerializationUtils.deserialize(new ByteArrayInputStream(body), ALLOWED_LIST_PATTERNS,
                    ClassUtils.getDefaultClassLoader()).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "解码失败";
    }



}
