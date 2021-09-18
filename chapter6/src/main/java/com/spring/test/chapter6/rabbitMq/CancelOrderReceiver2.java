//package com.spring.test.chapter6.rabbitMq;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * 取消订单消息的处理者
// * Created by macro on 2018/9/14.
// */
//@Component
//@RabbitListener(
//        queues = RabbitMqConfig.ORDER_TTL_QUEUE_NAME,
//        returnExceptions = "false"
//)
//public class CancelOrderReceiver2 {
//    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver2.class);
//    @RabbitHandler
//    public void handle(Long orderId){
//        LOGGER.info("第二个接收者受到信息:{}",orderId);
//
//    }
//
//    @RabbitHandler
//    public void handler2(String aaa){
//        LOGGER.info("第二个接收者受到信息:{}",aaa);
//    }
//}
