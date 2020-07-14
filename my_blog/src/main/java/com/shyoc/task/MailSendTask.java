package com.shyoc.task;

import com.shyoc.pojo.Comment;
import com.shyoc.pojo.MailConstants;
import com.shyoc.pojo.MailSendLog;
import com.shyoc.pojo.Message;
import com.shyoc.service.CommentService;
import com.shyoc.service.MailSendLogService;
import com.shyoc.service.MessageService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author: u
 * @create: 2020-07-13 10:13 上午
 * @description:
 */

//@Component
public class MailSendTask {

   /* @Autowired
    MailSendLogService mailSendLogService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    CommentService commentService;

    @Autowired
    MessageService messageService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void mailsend() {
        List<MailSendLog> list = mailSendLogService.findMailSendLogStatus();
        list.forEach((mailSendLog -> {
            if (mailSendLog.getCount() >= MailConstants.MAX_TRY_COUNT) {
                //重试次数超过三次，认为发送失败
                mailSendLogService.updateMailSendLogStatus(mailSendLog.getMsgId(), MailConstants.FAILURE);
            }else{
                Long needId = mailSendLog.getNeedId();
                String msgId = mailSendLog.getMsgId();
                mailSendLogService.updateCount(msgId,new Date());
                Boolean isComment = mailSendLog.getIsComment();
                if (isComment) {
                    Comment comment=commentService.findCommentById(needId);
                    rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, "comment.key.mail", comment, new CorrelationData(msgId));
                }else{
                    Message message = messageService.findMessageById(needId);
                    rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, "message.key.mail", message, new CorrelationData(msgId));
                }
            }
        }));
    }*/

}
