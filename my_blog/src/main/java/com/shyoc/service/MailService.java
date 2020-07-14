package com.shyoc.service;


import javax.mail.MessagingException;

public interface MailService {
    /**
     * 发送邮件给指定对象
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to, String subject, String content) throws MessagingException;
}
