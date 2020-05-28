package com.wwh.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwenhao
 * @description SendMessageController
 * @date 2020-05-28 11:22
 */
@RestController
@RequestMapping("send")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("notExistExchange")
    public void sendNotExistExchange() {
        // 发送消息到一个不存在的交换机上 此时只会触发ConfirmCallback
        rabbitTemplate.convertAndSend("notExistExchange", null, "发送消息到一个不存在的交换机上");
    }

    @GetMapping("notExistQueue")
    public void sendNotExistQueue() {
        // 发送消息到一个不存在的队列的交换机上 此时会触发ReturnCallback和ConfirmCallback
        rabbitTemplate.convertAndSend("notExistQueue", null, "发送消息到一个不存在的队列的交换机上");
    }

    @GetMapping("normal")
    public void sendNormalMessage() {
        // 发送消息到正常的交换机和队列上 此时只会触发ConfirmCallback
        rabbitTemplate.convertAndSend("testDirectExchange", "mail", "生产者发送的消息--");
    }
}
