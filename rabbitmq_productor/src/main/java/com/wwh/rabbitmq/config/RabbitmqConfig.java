package com.wwh.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangwenhao
 * @description MQ配置类
 * @date 2020-05-27 11:31
 */
@Configuration
public class RabbitmqConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 创建一个队列
     * @return
     */
    @Bean
    public Queue getMailQueue() {
        Queue queue = new Queue("mail_queue_1", true);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue getMailQueue2() {
        Queue queue = new Queue("mail_queue_2", true);
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(getDirectExchange()).with("mail");
        amqpAdmin.declareBinding(binding);
        return queue;
    }

    /**
     * 创建一个交换机
     * @return
     */
    @Bean
    public DirectExchange getDirectExchange() {

        // 定义指定类型交换机
        DirectExchange directExchange = new DirectExchange("testDirectExchange", true, false);

        // 定义指定类型交换机方式2
        // CustomExchange exchange = new CustomExchange("testDirectExchange", "direct", true, false);
        amqpAdmin.declareExchange(directExchange);
        return directExchange;
    }

    /**
     * 将队列与交换机绑定
     * @return
     */
    @Bean
    public Binding getBinding() {
        Binding binding = BindingBuilder.bind(getMailQueue()).to(getDirectExchange()).with("mail");
        amqpAdmin.declareBinding(binding);
        return binding;
    }

    /**
     * 自定义JSON消息序列化器
     * @return
     */
    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
