package com.example.rabbitmq.work;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class Comsumer2 {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectUtil.connectRabbit();

        channel.basicQos(1);

        channel.queueDeclare("work",false,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2号处理："+new String(body));
                // 手动确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
