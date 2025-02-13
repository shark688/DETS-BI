package com.zrd.springbootinit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class SingleSend {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        //创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
//        factory.setPort();
//        factory.setPassword();
        //创建链接
        try (Connection connection = factory.newConnection();
             //创建频道
             Channel channel = connection.createChannel()) {
            //创建消息队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}