package com.wwh.rabbitmq.productor;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wangwenhao
 * @description RabbitConfirmConfig
 * @date 2020-05-27 16:51
 */
@Service
public class RabbitConfirmConfig implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("接收到消息确认");
    }
}
