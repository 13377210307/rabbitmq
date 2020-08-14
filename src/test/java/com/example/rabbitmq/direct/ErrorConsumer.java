package com.example.rabbitmq.direct;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class ErrorConsumer {

    public static void main(String[] args) throws Exception {
        // 连接通道
        Channel channel = ConnectUtil.connectRabbit();

        String exchangeName = "direct";

        String type = "direct";

        // 声明交换机
        channel.exchangeDeclare(exchangeName,type);

        // 生成临时队列
        String queueName = channel.queueDeclare().getQueue();


        String routeKey = "error";

        //绑定队列：参数一：队列名称  参数二：交换机名  参数三：roterKey
        channel.queueBind(queueName,exchangeName,routeKey);

        //消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("错误日志消费者"+new String(body));
            }
        });

    }
}
