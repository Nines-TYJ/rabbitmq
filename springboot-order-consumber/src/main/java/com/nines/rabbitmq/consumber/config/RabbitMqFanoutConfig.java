package com.nines.rabbitmq.consumber.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:27
 */
@Configuration
public class RabbitMqFanoutConfig {

    // 1. 声明注册 Fanout 模式的交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange", true, false);
    }
    // 2. 声明队列
    @Bean
    public Queue smsFanoutQueue(){
        return new Queue("sms_fanout_queue", true);
    }

    @Bean
    public Queue duanxinFanoutQueue(){
        return new Queue("duanxin_fanout_queue", true);
    }

    @Bean
    public Queue emailFanoutQueue(){
        return new Queue("email_fanout_queue", true);
    }
    // 3. 完成绑定关系（交换机和队列的绑定）
    @Bean
    public Binding smsFanoutBinding(){
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding duanxinFanoutBinding(){
        return BindingBuilder.bind(duanxinFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailFanoutBinding(){
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }

}
