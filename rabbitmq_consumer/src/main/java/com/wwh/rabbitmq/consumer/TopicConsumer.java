package com.wwh.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author wangwenhao
 * @description TopicConsumer
 * @date 2020-05-27 16:04
 */
@Service
public class TopicConsumer {

    @RabbitListener(queues = {"topicQueue-1"})
    public void receiveTopicQueue1(Message message) {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        System.out.println("================receiveTopicQueue1====================");
    }

    @RabbitListener(queues = {"topicQueue-2"})
    public void receiveTopicQueue2(Message message) {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        System.out.println("================receiveTopicQueue2====================");
    }
}
