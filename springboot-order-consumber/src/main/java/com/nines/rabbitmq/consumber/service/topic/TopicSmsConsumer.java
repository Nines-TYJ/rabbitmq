package com.nines.rabbitmq.consumber.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:57
 */
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "#.sms.#"
))
@Service
public class TopicSmsConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms_topic——接收到了订单：" + message);
    }

}
