package com.example.rabbitmq.fanout;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class Comsumer1 {

    public static void main(String[] args) throws Exception {

        String exchangeName = "exchange";
        // 连接通道
        Channel channel = ConnectUtil.connectRabbit();

        // 声明交换机
        channel.exchangeDeclare(exchangeName,"fanout");

        //创建临时队列
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,exchangeName,"");

        // 消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
