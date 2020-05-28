package com.wwh.rabbitmq.consumer;

import com.wwh.rabbitmq.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 监听mail_queue_2队列 配合注解@RabbitHandler实现不同类型的消息由不同的方法执行
 * @author wangwenhao
 * @description 消费者2
 * @date 2020-05-27 13:56
 */
@Service
@RabbitListener(queues = {"mail_queue_2"})
public class Consumer2 {

    @RabbitHandler(isDefault = true)
    public void receiveMessage(Message message) throws InterruptedException {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("==================Consumer2-receiveMessage1====================");
    }

    @RabbitHandler
    public void receiveMessage(@Payload User user) throws InterruptedException {
        System.out.println(user);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("==================Consumer2-receiveMessage2====================");
    }

    @RabbitHandler
    public void receiveMessage(@Payload String message) throws InterruptedException {
        System.out.println(message);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("==================Consumer2-receiveMessage3====================");
    }
}
