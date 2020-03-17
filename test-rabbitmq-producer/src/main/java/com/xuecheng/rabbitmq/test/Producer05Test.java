package com.xuecheng.rabbitmq.test;

import com.xuecheng.rabbitmq.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer05Test {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendByTopics(){
        for (int i = 0; i < 5; i++) {
            String message = "sms email send user " + i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("Send message is " + message);
        }
    }
}
