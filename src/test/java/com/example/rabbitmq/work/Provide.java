package com.example.rabbitmq.work;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.Channel;

/**
 * 生产者生产的消息会被其他消费者进行平分
 */
public class Provide {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectUtil.connectRabbit();
        channel.queueDeclare("work",false,false,false,null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","work",null,(i+"work").getBytes());
        }
    }
}
