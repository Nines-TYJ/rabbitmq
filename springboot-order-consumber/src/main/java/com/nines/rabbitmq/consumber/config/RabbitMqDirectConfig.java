package com.nines.rabbitmq.consumber.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:27
 */
@Configuration
public class RabbitMqDirectConfig {

    // 1. 声明注册 Direct 模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange", true, false);
    }
    // 2. 声明队列
    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue", true);
    }

    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue("duanxin.direct.queue", true);
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("email.direct.queue", true);
    }
    // 3. 完成绑定关系（交换机和队列的绑定）
    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding duanxinDirectBinding(){
        return BindingBuilder.bind(duanxinDirectQueue()).to(directExchange()).with("duanxin");
    }

    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }

}
