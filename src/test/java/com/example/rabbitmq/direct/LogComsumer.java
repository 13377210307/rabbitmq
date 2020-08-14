package com.example.rabbitmq.direct;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class LogComsumer {

    public static void main(String[] args) throws Exception {
        //连接通道
        Channel channel = ConnectUtil.connectRabbit();

        String exchangeName = "direct";

        String type = "direct";

        //声明交换机
        channel.exchangeDeclare(exchangeName,type);

        // 创建临时队列
        String queueName = channel.queueDeclare().getQueue();

        String routeKey1 = "log";
        String routeKey2 = "log";
        String routeKey3 = "log";

        //绑定队列  1：临时队列名称，2：交换机名称、3：路由key
        channel.queueBind(queueName,exchangeName,routeKey1);
        channel.queueBind(queueName,exchangeName,routeKey2);
        channel.queueBind(queueName,exchangeName,routeKey3);

        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("日志消费者："+new String(body));
            }
        });
    }
}
