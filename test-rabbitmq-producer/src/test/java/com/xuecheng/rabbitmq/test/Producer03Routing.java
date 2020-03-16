package com.xuecheng.rabbitmq.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer03Routing {
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_ROUTING_INFORM = "exchange_routing_inform";
    public static void main(String[] args) throws IOException, TimeoutException {
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
            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
            //交换机和队列绑定
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING_INFORM, QUEUE_INFORM_EMAIL);
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING_INFORM, "inform");
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, QUEUE_INFORM_SMS);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, "inform");
            //发送邮件消息
            /**
             * 1、交换机名称
             * 2、routingkey，路由key
             * 3、消息属性
             * 4、消息内容
             */
            /*for (int i = 0; i < 10; i++) {
                String message = "email inform to user " + i;
                channel.basicPublish(EXCHANGE_ROUTING_INFORM,QUEUE_INFORM_EMAIL,null,message.getBytes());
                System.out.println("Email send message is " + message);
            }
            for (int i = 0; i < 10; i++) {
                String message = "sms inform to user " + i;
                channel.basicPublish(EXCHANGE_ROUTING_INFORM,QUEUE_INFORM_SMS,null,message.getBytes());
                System.out.println("Sms send message is " + message);
            }*/
            for (int i = 0; i < 10; i++) {
                String message = "inform to user " + i;
                channel.basicPublish(EXCHANGE_ROUTING_INFORM,"inform",null,message.getBytes());
                System.out.println("send message is " + message);
            }
            //发送短信邮件
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            /*if (channel != null){
                channel.close();
            }
            if (connection != null){
                connection.close();
            }*/
        }
    }
}
