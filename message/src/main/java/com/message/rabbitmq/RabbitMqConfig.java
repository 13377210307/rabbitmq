package com.message.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // 队列名称
    private static String QUEUE = "MESSAGE";

    // 交换机名称
    private static final String EXCHANGE = "MESSAGE";

    // 路由key
    private static final String ROUTE_KEY = "MESSAGE.*";

    // 队列
    /*@Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    // 交换机
    @Bean
    public Exchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // 交换机绑定队列
    public Binding binding(Queue queue,Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with()
    }*/

    // 验证码
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = EXCHANGE,type = "topic"),
                    key = {ROUTE_KEY}
            )
    })
    public void codeReceiver(String message) {
        System.out.println("验证码消费者："+message);
    }

    // 邮箱
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = EXCHANGE,type = "topic"),
                    key = {ROUTE_KEY}
            )
    })
    public void mailReceiver(String message) {
        System.out.println("邮箱消费者："+message);
    }




}
