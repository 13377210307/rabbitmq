package com.example.rabbitmq.hello;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectUtil.connectRabbit();
        // 与生产者保持一致
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        //参数一：消息提供者队列名称
        //参数二：消息自动确认机制
        //参数三：消费回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){

            // body:消息内容
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("==========================="+new String(body));
            }
        });
    }
}
