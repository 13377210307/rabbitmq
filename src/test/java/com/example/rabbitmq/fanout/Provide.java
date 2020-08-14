package com.example.rabbitmq.fanout;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.Channel;

/**
 * 可以有多个消费者
 * 每个消费者有自己的队列
 * 每个队列都要绑定到交换机
 * 生产者发送的消息智能发送到交换机，交换机决定发给哪个队列，生产者无法决定
 * 交换机把消息发送给绑定过的所有队列
 * 队列的消费者都能拿到消息，实现一条消息被多个消费者消费
 */
public class Provide {

    public static void main(String[] args) throws Exception {

        // 连接通道
        Channel channel = ConnectUtil.connectRabbit();

        String exchangeName = "exchange";

        // 声明交换机：参数一：交换机名称，参数二：交换模式
        channel.exchangeDeclare(exchangeName,"fanout");

        // 生产消息
        channel.basicPublish(exchangeName,"",null,"fanout 模式".getBytes());

    }
}
