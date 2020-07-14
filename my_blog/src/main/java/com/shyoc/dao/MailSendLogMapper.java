package com.shyoc.dao;

import com.shyoc.pojo.MailSendLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: u
 * @create: 2020-07-13 9:50 上午
 * @description:
 */
@Mapper
public interface MailSendLogMapper {
    Integer updateMailSendLogStatus(@Param("msgId") String msgId,@Param("status") Integer status);

    Integer insert(MailSendLog mailSendLog);

    List<MailSendLog> findMailSendLogStatus();

    Integer updateCount(@Param("msgId") String msgId,@Param("updateTime") Date updateTime);
}
