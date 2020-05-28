package com.wwh.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author wangwenhao
 * @description FanoutConsumer
 * @date 2020-05-27 16:35
 */
@Service
public class FanoutConsumer {

    @RabbitListener(queues = {"fanoutQueue-1"})
    public void receiveFanoutQueue1(Message message) {
        System.out.println("fanoutQueue-1 ========= " + message);
        System.out.println("fanoutQueue-1 ========= " + new String(message.getBody()));
        System.out.println("==============fanoutQueue-1================");
    }

    @RabbitListener(queues = {"fanoutQueue-2"})
    public void receiveFanoutQueue2(Message message) {
        System.out.println("fanoutQueue-2 ========= " + message);
        System.out.println("fanoutQueue-2 ========= " + new String(message.getBody()));
        System.out.println("==============fanoutQueue-2================");
    }

    @RabbitListener(queues = {"fanoutQueue-3"})
    public void receiveFanoutQueue3(Message message) {
        System.out.println("fanoutQueue-3 ========= " + message);
        System.out.println("fanoutQueue-3 ========= " + new String(message.getBody()));
        System.out.println("==============fanoutQueue-3================");
    }
}
