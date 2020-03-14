package com.xuecheng.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer01 {
    public static final String QUEUE = "helloworld";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            //连接工厂设置主机名，端口，用户名，密码，虚拟名称
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");

            //创建与RabbitMQ的连接
            connection = factory.newConnection();
            //创建与Channel的连接
            channel = connection.createChannel();
            System.out.println("product ip："+connection.getPort());
            /**
             * 声明队列，如果rabbit中没有则创建此队列
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否删除此队列
             * param5:队列参数
             */
            channel.queueDeclare(QUEUE,true,false,false,null);
            String message = "hellworld 小星星 " + System.currentTimeMillis();
            /**
             * 消息发布方法
             *param1:Exchange的名称，没有指定则用默认的
             * param2:消息的路由key,是用于Exchange将消息转发到指定的消息队列
             * param3:消息包含的属性
             * param4:消息体
             */
            channel.basicPublish("",QUEUE,null,message.getBytes());
            System.out.println("Send message is "+message+" ");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            /*if (channel != null){
                channel.close();
            }
            if (connection != null){
                connection.close();
            }*/
        }
    }
}
