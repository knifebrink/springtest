package com.spring.test.chapter6.rabbitMq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 * Created by macro on 2018/9/14.
 */
@Configuration
public class RabbitMqConfig {
    // easy队列名称
    public static final String EASY_QUEUE_NAME = "easyQueue";
    // easy2队列名称
    public static final String EASY_QUEUE_NAME_2 = "easyQueue2";

    // order队列设置
    // 队列名称
    public static final String ORDER_QUEUE_NAME = "order.queue.name";
    // 交换机名称
    public static final String ORDER_EXCHANGE = "order.exchange";
    // 路由键
    public static final String ORDER_ROUTE_KEY = "order.route.key";

    // order ttl 队列设置
    public static final String ORDER_TTL_QUEUE_NAME = "order.ttl.queue.name";
    public static final String ORDER_TTL_EXCHANGE = "order.ttl.exchange";
    public static final String ORDER_TTL_ROUTE_KEY = "order.ttl.route.key";

    /**
     * 订单消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(ORDER_EXCHANGE)
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列所绑定的交换机
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(ORDER_TTL_EXCHANGE)
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费队列
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME);
    }

    /**
     * 订单延迟队列（死信队列）
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(ORDER_TTL_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", ORDER_EXCHANGE)//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", ORDER_ROUTE_KEY)//到期后转发的路由键
                .build();
    }

    /**
     * 将订单队列绑定到交换机，三者绑定
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(ORDER_ROUTE_KEY);
    }

    /**
     * 将订单延迟队列绑定到交换机，交换机，队列，路由键绑定
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect,Queue orderTtlQueue){
        System.out.println(""+orderTtlQueue.getName());
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(ORDER_TTL_ROUTE_KEY);
    }

    /**
     * easyReceiver测试
     */
    @Bean
    public Queue easyQueue() {
        return new Queue(RabbitMqConfig.EASY_QUEUE_NAME);
    }

    /**
     * easyReceiver测试2
     */
    @Bean
    public Queue easyQueue2() {
        return new Queue(RabbitMqConfig.EASY_QUEUE_NAME_2);
    }

}
