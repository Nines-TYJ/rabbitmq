package com.nines.rabbitmq.producer.controller;

import com.nines.rabbitmq.producer.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author TYJ
 * @date 2021/7/14 上午10:37
 */
@RestController
@RequestMapping("/rabbitmq")
public class TestController {

    @Resource
    OrderService orderService;

    @GetMapping("/send")
    public String sendMessage(){
        orderService.directMarkOrder("3", "003", 12, "ttl");
        return "ok";
    }

}
