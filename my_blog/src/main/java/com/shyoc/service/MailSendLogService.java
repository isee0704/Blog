package com.shyoc.service;

import com.shyoc.pojo.MailSendLog;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: u
 * @create: 2020-07-13 9:30 上午
 * @description:
 */

public interface MailSendLogService {



    Integer updateMailSendLogStatus(String msgId, Integer status);

    Integer insert(MailSendLog mailSendLog);

    List<MailSendLog> findMailSendLogStatus();

    Integer updateCount(String msgId, Date updateTime);
}
