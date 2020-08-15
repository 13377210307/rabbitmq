package com.message.service.impl;

import com.message.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 队列名称
    private static String QUEUE = "MESSAGE";

    // 交换机名称
    private static String EXCHANGE = "MESSAGE";

    // 路由key
    private static String ROUTE_KEY = "MESSAGE.SEND";

    @Override
    public Boolean message() {
        this.rabbitTemplate.convertAndSend(QUEUE,ROUTE_KEY,"短信发送");
        return true;
    }

}
