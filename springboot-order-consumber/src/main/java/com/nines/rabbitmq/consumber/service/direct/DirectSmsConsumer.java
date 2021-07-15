package com.nines.rabbitmq.consumber.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:57
 */
@RabbitListener(queues = "sms.direct.queue")
@Service
public class DirectSmsConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms_direct——接收到了订单：" + message);
    }

}
