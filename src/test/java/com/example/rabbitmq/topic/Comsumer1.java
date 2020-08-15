package com.example.rabbitmq.topic;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class Comsumer1 {

    public static void main(String[] args) throws Exception {
        //连接通道
        Channel channel = ConnectUtil.connectRabbit();

        String exchangeName = "topic";

        String type = "topic";

        // 声明交换机
        channel.exchangeDeclare(exchangeName,type);

        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();

        //定义routerKey
        String roukey = "user.#";

        //绑定队列
        channel.queueBind(queueName,exchangeName,roukey);

        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });

    }
}
