package com.spring.test.chapter6.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 * Created by macro on 2018/9/14.
 * 在没有此监听器时，会超时发送到另一个队列
 */
@Component
@RabbitListener(queues = RabbitMqConfig.ORDER_QUEUE_NAME)
public class CancelOrderReceiver {
    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver.class);

    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("第一个接收者:{}",orderId);

    }
    @RabbitHandler
    public void handle2(String ob, Message message){
        LOGGER.info("第一个接收者String类型:{} 交换机和路由键信息:{}",ob,
                ""+message.getMessageProperties().getReceivedExchange()+
                        " | "+message.getMessageProperties().getReceivedRoutingKey());
        // 查看Message所有信息就打开注释
//        LOGGER.info("第一个接收者打印msg信息：{}",message);

    }

    @RabbitHandler
    public void handle3(Float ob){
        LOGGER.info("第一个接收者:{}",ob);

    }
}
