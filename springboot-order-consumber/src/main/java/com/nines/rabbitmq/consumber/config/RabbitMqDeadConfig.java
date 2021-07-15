package com.nines.rabbitmq.consumber.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:27
 */
@Configuration
public class RabbitMqDeadConfig {

    // 1. 声明注册 Direct 模式的交换机
    @Bean
    public DirectExchange deadDirectExchange(){
        return new DirectExchange("direct_dead_exchange", true, false);
    }
    // 2. 声明队列
    @Bean
    public Queue deadDirectQueue(){
        return new Queue("dead.direct.queue");
    }
    // 3. 完成绑定关系（交换机和队列的绑定）
    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(deadDirectExchange()).with("dead");
    }

}
