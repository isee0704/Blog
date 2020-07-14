package com.shyoc.config;

import com.shyoc.pojo.MailConstants;
import com.shyoc.service.MailSendLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: u
 * @create: 2020-07-12 7:38 下午
 * @description:
 */
@Configuration
public class RabbitmqConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    MailSendLogService mailSendLogService;


    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data,ask,cause)->{
            String msgId = data.getId(); //消息唯一标识
            if (ask) { //消息发送成功
                //logger.info("msgId:{},消息发送成功！",msgId);
                mailSendLogService.updateMailSendLogStatus(msgId, MailConstants.SUCCESS);
            }else{
               // logger.info("消息发送失败！");
            }
        });
        rabbitTemplate.setReturnCallback((message,replyCode,replyText,exchange,routingKey)->{
            //logger.info("消息发送失败！");
        });
        return rabbitTemplate;
    }



    @Bean
    Queue commentQueue() {
        return new Queue(MailConstants.COMMENT_QUEUE_NAME,true);
    }

    @Bean
    Queue messageQueue() {
        return new Queue(MailConstants.MESSAGE_QUEUE_NAME,true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(MailConstants.MAIL_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding commentBinding() {
        return BindingBuilder.bind(commentQueue()).to(topicExchange()).with(MailConstants.COMMENT_MAIL_ROUTING_KEY_NAME);
    }

    @Bean
    Binding messageBinding() {
        return BindingBuilder.bind(messageQueue()).to(topicExchange()).with(MailConstants.MESSAGE_MAIL_ROUTING_KEY_NAME);
    }
}
