package com.example.rabbitmq.topic;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.Channel;

public class Provide {

    public static void main(String[] args) throws Exception {
        // 连接通道
        Channel channel = ConnectUtil.connectRabbit();

        String exchangeName = "topic";

        String type = "topic";

        //定义交换机
        channel.exchangeDeclare(exchangeName,type);

        // 定义路由
        String roteKey = "user.save.insert";

        channel.basicPublish(exchangeName,roteKey,null,"topic模式生产消息".getBytes());
    }
}
