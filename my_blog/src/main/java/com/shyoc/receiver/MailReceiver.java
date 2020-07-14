package com.shyoc.receiver;

import com.rabbitmq.client.Channel;
import com.shyoc.pojo.Comment;
import com.shyoc.pojo.MailConstants;
import com.shyoc.pojo.Message;
import com.shyoc.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

/**
 * @author: u
 * @create: 2020-07-12 7:57 下午
 * @description:
 */
@Component
public class MailReceiver {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.fromMail.addr}")
    private String defaultTo;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.COMMENT_QUEUE_NAME)
    public void commentReceiver(org.springframework.messaging.Message message, Channel channel) throws IOException {
        Comment comment = (Comment) message.getPayload();//消息主体
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);//用于消息确认
        String msgId = (String) headers.get("spring_returned_message_correlation");//消息的唯一标识
        if (redisTemplate.opsForSet().isMember("msg", msgId)) {
      //      logger.info("消息已经被消费！");
            channel.basicAck(tag,false);//需要确认消费，否则会退回队列等待消费
            return;
        }
      //  logger.info("comment mail{}",comment);
        //邮件发送
        Context context = new Context();
        String to = defaultTo;
        Comment parentComment = comment.getParentComment();
        to = parentComment == null ? defaultTo : parentComment.getUser().getEmail();
        if(parentComment != null) {
            context.setVariable("parentComment", parentComment);
            Date parentCommentCreateTime = new Date(parentComment.getCreateTime());
            context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
        }
        context.setVariable("comment", comment);
        context.setVariable("isComment", true);
        Date commentCreateTime = new Date((comment).getCreateTime());
        context.setVariable("commentCreateTime", commentCreateTime);
        String emailContent = templateEngine.process("emailTemplate", context);
        try {
            mailService.sendMail(to, "主题: 博客回复通知", emailContent);
            redisTemplate.opsForSet().add("msg", msgId);
        //    logger.info("msgId{},邮件发送成功",msgId);
            channel.basicAck(tag,false);
        } catch (MessagingException e) {
         //   logger.info("邮件发送失败！");
            channel.basicNack(tag, false, true);//发送失败，退回队列，第一个参数表示是否批处理，第二个参数true表示退回，false则丢弃
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = MailConstants.MESSAGE_QUEUE_NAME)
    public void messageReceiver(org.springframework.messaging.Message message1, Channel channel) throws IOException {
        logger.info("messageReceivermessageReceiver=---------messageReceiverfa");
        Message message = (Message) message1.getPayload();//消息主体
        MessageHeaders headers = message1.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);//用于消息确认
        String msgId = (String) headers.get("spring_returned_message_correlation");//消息的唯一标识
        if (redisTemplate.opsForSet().isMember("msg1", msgId)) {
          //  logger.info("消息已经被消费！");
            channel.basicAck(tag,false);//需要确认消费，否则会退回队列等待消费
            return;
        }
        //邮件发送
        Context context = new Context();
        String to = defaultTo;
        Message parentMessage = message.getParentMessage();
        to = parentMessage == null ? defaultTo : parentMessage.getUser().getEmail();
        if(parentMessage != null) {
            context.setVariable("parentComment", parentMessage);
            Date parentCommentCreateTime = new Date(parentMessage.getCreateTime());
            context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
        }
        message.setCreateTime(System.currentTimeMillis());
        context.setVariable("comment", message);
        context.setVariable("isComment", false);
        Date commentCreateTime = new Date(message.getCreateTime());
        context.setVariable("commentCreateTime", commentCreateTime);
        String emailContent = templateEngine.process("emailTemplate", context);
        try {
            mailService.sendMail(to, "主题: 博客回复通知", emailContent);
            redisTemplate.opsForSet().add("msg1", msgId);
            logger.info("msgId{},邮件发送成功",msgId);
            logger.info("to--{}",to);
            channel.basicAck(tag,false);
        } catch (MessagingException e) {
            logger.info("邮件发送失败！");
            channel.basicNack(tag, false, true);//发送失败，退回队列，第一个参数表示是否批处理，第二个参数true表示退回，false则丢弃
            e.printStackTrace();
        }
    }
}
