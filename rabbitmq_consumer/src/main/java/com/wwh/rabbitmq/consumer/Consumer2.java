package com.wwh.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangwenhao
 * @description 消费者1
 * @date 2020-05-27 13:56
 */
@Service
public class Consumer2 {

    @RabbitListener(queues = {"mail_queue_2"})
    public void receiveMessage(Message message) throws InterruptedException {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("==================Consumer2====================");
    }
}
