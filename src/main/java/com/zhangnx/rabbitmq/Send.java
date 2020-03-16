package com.zhangnx.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Send {
    //定义队列名字
    private final static String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        //创建连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //为通道指明队列(声明队列)
        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        //发布消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        //关闭连接
        channel.close();
        connection.close();
    }

}
