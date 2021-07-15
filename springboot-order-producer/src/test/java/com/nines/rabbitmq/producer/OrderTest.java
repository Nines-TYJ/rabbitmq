package com.nines.rabbitmq.producer;

import com.nines.rabbitmq.producer.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author TYJ
 * @date 2021/7/9 下午4:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderTest {

    @Resource
    OrderService orderService;

    @Test
    public void fanoutOrderTest(){
        orderService.fanoutMarkOrder("1", "001", 5);
        orderService.fanoutMarkOrder("2", "002", 1);
    }

    @Test
    public void directOrderTest(){
        orderService.directMarkOrder("3", "003", 12, "ttl");
//        orderService.directMarkOrder("4", "004", 20);
    }

    @Test
    public void topicOrderTest(){
        orderService.topicMarkOrder("5", "005", 99);
    }

    @Test
    public void ttlMessageTest(){
        for (int i = 0; i < 11; i++) {
            orderService.ttlMessageMarkOrder("6", "006", 6, "ttl");
        }
    }

}
