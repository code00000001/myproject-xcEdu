package rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer05TestTopicSpringBoot {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public static void main(String[] args) {
        SpringApplication.run(Consumer05TestTopicSpringBoot.class, args);
    }


}
