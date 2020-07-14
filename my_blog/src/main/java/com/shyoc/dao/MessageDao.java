package com.shyoc.dao;

import com.shyoc.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MessageDao {

    /**
     * 查询留言列表
     * @return
     */
    List<Message> findMessageList();

    /**
     * 保存留言信息
     * @param message
     * @return
     */
    int saveMessage(Message message);

    Message findMessageById(Long messageId);
}
