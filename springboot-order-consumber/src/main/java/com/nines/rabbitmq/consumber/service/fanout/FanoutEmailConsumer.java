package com.nines.rabbitmq.consumber.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:57
 */
@RabbitListener(queues = "email_fanout_queue")
@Service
public class FanoutEmailConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email_fanout——接收到了订单：" + message);
    }

}
