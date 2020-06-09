package com.wwh.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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


    /**
     * 创建Topic交换机
     * @return
     */
    @Bean
    public Exchange getTopicExchange() {
        Exchange topicExchange = ExchangeBuilder.topicExchange("topicExchange").durable(true).build();
        amqpAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    /**
     * 创建一个Queue绑定到topicExchange上 只有routingKey为topic.man时消息才会发到该队列上
     * @return
     */
    @Bean
    public Queue getTopicQueue1() {
        Queue queue = QueueBuilder.durable("topicQueue-1").build();
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(getTopicExchange()).with("topic.man").noargs();
        amqpAdmin.declareBinding(binding);
        return queue;
    }

    /**
     * 创建一个Queue绑定到topicExchange上 只有routingKey为topic.开头的消息都会发到该队列上
     * @return
     */
    @Bean
    public Queue getTopicQueue2() {
        Queue queue = QueueBuilder.durable("topicQueue-2").build();
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(getTopicExchange()).with("topic.#").noargs();
        amqpAdmin.declareBinding(binding);
        return queue;
    }

    /**
     * 创建一个FanoutExchange并绑定三个队列 绑定到该交换机上的队列都会收到相同消息，无需routingKey
     * @return
     */
    @Bean
    public FanoutExchange getFanoutExchange() {

        // 创建一个FanoutExchange交换机并声明
        FanoutExchange fanoutExchange = new FanoutExchange("fanoutExchange", true, false);
        amqpAdmin.declareExchange(fanoutExchange);

        // 创建队列1并绑定到交换机上
        Queue fanoutQueue1 = QueueBuilder.durable("fanoutQueue-1").build();
        amqpAdmin.declareQueue(fanoutQueue1);
        Binding binding1 = BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
        amqpAdmin.declareBinding(binding1);

        // 创建队列2并绑定到交换机上
        Queue fanoutQueue2 = QueueBuilder.durable("fanoutQueue-2").build();
        amqpAdmin.declareQueue(fanoutQueue2);
        Binding binding2 = BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
        amqpAdmin.declareBinding(binding2);

        // 创建队列3并绑定到交换机上
        Queue fanoutQueue3 = QueueBuilder.durable("fanoutQueue-3").build();
        amqpAdmin.declareQueue(fanoutQueue3);
        Binding binding3 = BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
        amqpAdmin.declareBinding(binding3);

        return fanoutExchange;
    }

    @Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConverter());
        // 设置开启Mandatory才能触发回调函数 无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 设置消息发送到交换机确认回调逻辑
        rabbitTemplate.setConfirmCallback((correlationData,ack ,cause) ->{
            System.out.println("ConfirmCallBack:    相关数据--" + correlationData);
            System.out.println("ConfirmCallBack:    确认情况--" + ack);
            System.out.println("ConfirmCallBack:    原因--" + cause);
        });

        // 设置消息发送到队列确认回调逻辑
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("ReturnCallback:    消息--" + message);
            System.out.println("ReturnCallback:    回应码--" + replyCode);
            System.out.println("ReturnCallback:    回应消息--" + replyText);
            System.out.println("ReturnCallback:    交换机--" + exchange);
            System.out.println("ReturnCallback:    路由键--" + routingKey);
        }));

        return rabbitTemplate;
    }

}
