package com.nines.rabbitmq.producer.config;

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
public class RabbitmqConfig {

    // 1. 声明注册 Fanout 模式的交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange", true, false);
    }
    // 2. 声明队列
    @Bean
    public Queue smsQueue(){
        return new Queue("sms_fanout_queue", true);
    }

    @Bean
    public Queue duanxinQueue(){
        return new Queue("duanxin_fanout_queue", true);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue("email_fanout_queue", true);
    }
    // 3. 完成绑定关系（交换机和队列的绑定）
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding duanxinBinding(){
        return BindingBuilder.bind(duanxinQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

}
