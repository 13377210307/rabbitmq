package com.example.rabbitmq.direct;

import com.example.rabbitmq.utils.ConnectUtil;
import com.rabbitmq.client.Channel;

public class Provide {

    public static void main(String[] args) throws Exception {
        // 连接通道
        Channel channel = ConnectUtil.connectRabbit();

        String exchangeName = "direct";

        String exchangeType = "direct";

        // 声明交换机：参数一：交换机名称：参数二：交换机类型
        channel.exchangeDeclare(exchangeName,exchangeType);

        String routeKey = "error";

        // 生产消息 参数一：交换机名称；参数二：路由key；参数三：携带额外参数；参数四：消息内容
        channel.basicPublish(exchangeName,routeKey,null,"路由模式交换机产生消息".getBytes());

    }
}
