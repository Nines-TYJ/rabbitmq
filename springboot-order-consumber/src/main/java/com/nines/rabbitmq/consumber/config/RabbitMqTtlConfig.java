package com.nines.rabbitmq.consumber.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:27
 */
@Configuration
public class RabbitMqTtlConfig {

    // 1. 声明注册 Direct 模式的交换机
    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("direct_order_exchange", true, false);
    }
    // 2. 声明队列
    @Bean
    public Queue ttlDirectQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        args.put("x-max-length", 5);
        args.put("x-dead-letter-exchange", "direct_dead_exchange");
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.direct.queue", true, false, false, args);
    }

    @Bean
    public Queue ttlMessageDirectQueue(){
        return new Queue("ttl.message.direct.queue", true);
    }

    // 3. 完成绑定关系（交换机和队列的绑定）
    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue()).to(ttlDirectExchange()).with("ttl");
    }

    @Bean
    public Binding ttlMessageDirectBinding(){
        return BindingBuilder.bind(ttlMessageDirectQueue()).to(ttlDirectExchange()).with("ttl");
    }

}
