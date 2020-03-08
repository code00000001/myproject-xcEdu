package com.xuecheng.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer01 {
    public static final String QUEUE = "helloworld";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            //连接工厂设置主机名，端口，用户名，密码，虚拟名称
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");

            //创建于RabbitMq的连接
            connection = factory.newConnection();
            //创建与channel的连接
            channel = connection.createChannel();
            /**
             * 声明队列，如果rabbit中没有则创建此队列
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否删除此队列
             * param5:队列参数
             */
            channel.queueDeclare(QUEUE,true,false,false,null);

            //定义消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                /**
                 * 消费者接收消息调用此方法
                 * @param consumerTag 消费者标签，在channel.basicConsumer()去指定
                 * @param envelope 消息包的内容，获取交换机，路由key，消息ID
                 * @param properties
                 * @param body
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //获取交换机
                    String exchange = envelope.getExchange();
                    //获取路由key
                    String routingKey = envelope.getRoutingKey();
                    //获取消息id
                    long id = envelope.getDeliveryTag();
                    //消息内容
                    String message = new String(body, "utf-8");
                    System.out.println("receive message " + message);
                }
            };
            /**
             * 监听队列
             * param1：队列名称
             * param2：是否自动回复。true为自动回复，mq接收到回复会删除消息，false要手动回复
             * param3:消费消息的方法，消费者接收到消息后执行的方法
             */
            channel.basicConsume(QUEUE, true, defaultConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel != null){
                channel.close();
            }
            if (connection != null){
                connection.close();
            }
        }

    }
}
