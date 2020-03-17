package rabbitmq.test;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitmqConfig;

@Component
public class Consumer05Test {
    @RabbitListener(queues = RabbitmqConfig.QUEUE_INFORM_EMAIL)
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println("email receive " + msg);
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_INFORM_SMS)
    public void receive_sms(String msg,Message message,Channel channel){
        System.out.println("sms receive " + msg);
    }
}
