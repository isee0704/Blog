package com.shyoc.service.impl;

import com.shyoc.dao.MailSendLogMapper;
import com.shyoc.pojo.MailSendLog;
import com.shyoc.service.MailSendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author: u
 * @create: 2020-07-13 9:31 上午
 * @description:
 */
@Service
public class MailSendLogServiceImpl implements MailSendLogService {

    @Autowired
    MailSendLogMapper mailSendLogMapper;

    @Override
    public Integer updateMailSendLogStatus(String msgId, Integer status) {
        return mailSendLogMapper.updateMailSendLogStatus(msgId,status);
    }

    @Override
    public Integer insert(MailSendLog mailSendLog) {
        return mailSendLogMapper.insert(mailSendLog);
    }

    @Override
    public List<MailSendLog> findMailSendLogStatus() {
        return mailSendLogMapper.findMailSendLogStatus();
    }

    @Override
    public Integer updateCount(String msgId, Date updateTime) {
        return mailSendLogMapper.updateCount(msgId,updateTime);
    }
}
