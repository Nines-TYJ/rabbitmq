package com.nines.rabbitmq.consumber.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:57
 */
@RabbitListener(queues = "email.direct.queue")
@Service
public class DirectEmailConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email_direct——接收到了订单：" + message);
    }

}
