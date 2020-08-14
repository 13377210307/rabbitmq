package com.example.rabbitmq.hello;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.Channel;

/**
 * Hello模式
 * 1：通过工厂进行与rabbitmq的连接
 * 2：连接通道
 * 3：生产消息
 */
public class Provide {

    public static void main(String[] args) throws Exception {
        // 连接通道
        Channel channel = ConnectUtil.connectRabbit();

        // 生产消息 参数一：队列名称  参数二：是否持久化  参数三：是否独占队列  参数四：是否自动删除  参数五：其他属性
        channel.queueDeclare("hello",false,false,false,null);
        channel.basicPublish("","hello",null,"hello".getBytes());

    }
}
