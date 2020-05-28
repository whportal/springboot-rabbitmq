package com.wwh.rabbitmq;

import com.wwh.rabbitmq.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangwenhao
 * @description 生产者测试
 * @date 2020-05-27 13:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage() {
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("testDirectExchange", "mail", "生产者发送的消息--" + i);
        }
    }

    @Test
    public void sendObjectMessage() {
        User user = new User(18, "风清扬", "华山");
        rabbitTemplate.convertAndSend("testDirectExchange", "mail",user);
    }

    @Test
    public void sendTopicMessage() {
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", "这是一条Topic消息");
    }

    @Test
    public void sendFanoutMessage() {
        rabbitTemplate.convertAndSend("fanoutExchange",null,"这是一条Fanout消息");
    }

    @Test
    public void sendNotExistExchange() {
        // 发送消息到一个不存在的交换机上
        rabbitTemplate.convertAndSend("notExistExchange",null,"发送消息到一个不存在的交换机上");
    }
}
