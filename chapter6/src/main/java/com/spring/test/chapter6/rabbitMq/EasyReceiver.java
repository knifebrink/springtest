package com.spring.test.chapter6.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 * Created by macro on 2018/9/14.
 */
@Component
@RabbitListener(queues = RabbitMqConfig.EASY_QUEUE_NAME)
public class EasyReceiver {
    private static Logger LOGGER =LoggerFactory.getLogger(EasyReceiver.class);
    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("easyQueue接收Long类型:{}",orderId);

    }

    @RabbitHandler
    public void handler2(String data){
        LOGGER.info("easyQueue接收:{}",data);
    }

    @RabbitHandler
    public void handler4(RabbitMqUser data){
        LOGGER.info("easyQueue接收RabbitMqUser:{}",data);
    }

//    // 好像会报错
//    @RabbitHandler
//    public void handler3(Object data){
//        LOGGER.info("easyQueue接收Object所有:{}",data);
//    }


}
