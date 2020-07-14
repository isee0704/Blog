package com.shyoc.aspect;

import com.shyoc.pojo.Comment;
import com.shyoc.pojo.Message;
import com.shyoc.service.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;


public class SendMailAspect {
    /*@Value("${mail.fromMail.addr}")
    private String defaultTo;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Pointcut("execution(* com.shyoc.service.CommentService.saveComment(..))||execution(* com.shyoc.service.MessageService.saveMessage(..))")
    public void sendMail() {

    }

    @AfterReturning("sendMail()")
    @Async
    public void doAfterReturning(JoinPoint joinPoint) {
        Object arg = joinPoint.getArgs()[0];
        Context context = new Context();
        String to = defaultTo;
        if (arg instanceof Comment) {
            Comment parentComment = ((Comment) arg).getParentComment();
            to = parentComment == null ? defaultTo : parentComment.getUser().getEmail();
            if(parentComment != null) {
                context.setVariable("parentComment", parentComment);
                Date parentCommentCreateTime = new Date(parentComment.getCreateTime());
                context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
            }
            context.setVariable("comment", (Comment) arg);
            context.setVariable("isComment", true);
            Date commentCreateTime = new Date(((Comment) arg).getCreateTime());
            context.setVariable("commentCreateTime", commentCreateTime);
        }
        else if (arg instanceof Message) {
            Message parentMessage = ((Message) arg).getParentMessage();
            to = parentMessage == null ? defaultTo : parentMessage.getUser().getEmail();
            if(parentMessage != null) {
                context.setVariable("parentComment", parentMessage);
                Date parentCommentCreateTime = new Date(parentMessage.getCreateTime());
                context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
            }
            Message message = (Message) arg;
            message.setCreateTime(System.currentTimeMillis());
            context.setVariable("comment", message);
            context.setVariable("isComment", false);
            Date commentCreateTime = new Date(((Message) arg).getCreateTime());
            context.setVariable("commentCreateTime", commentCreateTime);
        }
        context.setVariable("create", 1555654688L);
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendMail(to, "主题: 博客回复通知", emailContent);
    }*/
}
