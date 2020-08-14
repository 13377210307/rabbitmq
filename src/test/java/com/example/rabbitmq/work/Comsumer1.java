package com.example.rabbitmq.work;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 当某一消费者耗时较长时，我们不应该进行消息平分，所以需要启动手动确认机制，并且只允许确认一个消息
 */

public class Comsumer1 {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectUtil.connectRabbit();

        channel.basicQos(1);

        channel.queueDeclare("work",false,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 沉睡2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1号处理："+new String(body));
                // 手动确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
