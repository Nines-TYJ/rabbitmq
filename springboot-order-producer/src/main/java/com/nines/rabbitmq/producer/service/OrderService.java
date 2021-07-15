package com.nines.rabbitmq.producer.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:14
 */
@Service
public class OrderService {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * fanout模式模拟用户下单
     * @param userId 用户id
     * @param productId 商品id
     * @param num 数量
     */
    public void fanoutMarkOrder(String userId, String productId, int num){
        // 1. 根据商品Id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功：" + orderId);
        // 3. 通过MQ来完成消息的分发
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    /**
     * direct模式模拟用户下单
     * @param userId 用户id
     * @param productId 商品id
     * @param num 数量
     */
    public void directMarkOrder(String userId, String productId, int num, String key){
        // 1. 根据商品Id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功：" + orderId);
        // 3. 通过MQ来完成消息的分发
        String exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, key, orderId);
    }

    /**
     * topic模式模拟用户下单
     * @param userId 用户id
     * @param productId 商品id
     * @param num 数量
     */
    public void topicMarkOrder(String userId, String productId, int num){
        // 1. 根据商品Id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功：" + orderId);
        // 3. 通过MQ来完成消息的分发
        String exchangeName = "topic_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "com.sms.duanxin", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "email.sms", orderId);
    }


    /**
     * topic模式模拟用户下单
     * @param userId 用户id
     * @param productId 商品id
     * @param num 数量
     */
    public void ttlMessageMarkOrder(String userId, String productId, int num, String key){
        // 1. 根据商品Id查询库存是否充足
        // 2. 保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功：" + orderId);
        // 3. 通过MQ来完成消息的分发
        String exchangeName = "direct_order_exchange";
        // 设置消息属性
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setExpiration("5000");
            properties.setContentEncoding("UTF-8");
            return message;
        };
        rabbitTemplate.convertAndSend(exchangeName, key, orderId, messagePostProcessor);
    }

}
