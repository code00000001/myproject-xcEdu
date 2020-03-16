package com.xuecheng.rabbitmq.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer03RoutingSms {
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_ROUTING_INFORM = "exchange_routing_inform";
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            //第一步：创建与MQ的connection连接
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            //创建一个连接
            connection = factory.newConnection();
            //第二步：创建与交换机的通道，每个通道就是一个会话
            channel = connection.createChannel();
            //第三步：声明交换机
            //第三步：声明交换机
            /**
             * EXCHANGE_FANOUT_INFORM: 交换机名称
             * FANOUT：交换机类型，fanout对应，publish/subscribe
             */
            channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM, BuiltinExchangeType.DIRECT);
            //第四步：声明队列
            /**
             * 1、队列名称
             * 2、是否持久化
             * 3、是否独占此队列
             * 4、队列不用是否自动删除
             * 5、参数
             */
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
            //交换机和队列绑定
            //channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, QUEUE_INFORM_SMS);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, "inform");
            //定义消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("sms receive message is " + message);
                }
            };
            //监听队列
            channel.basicConsume(QUEUE_INFORM_SMS, true, defaultConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
