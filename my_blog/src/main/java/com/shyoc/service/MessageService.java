package com.shyoc.service;

import com.shyoc.annotation.ClearRedisCache;
import com.shyoc.pojo.Message;
import com.shyoc.service.impl.UserServiceImpl;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MessageService {

    /**
     * 查询留言列表
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Message> findMessageList();

    /**
     * 保存留言信息
     * @param message
     * @param request
     * @return
     */
    @ClearRedisCache(cascade = {UserServiceImpl.class})
    int saveMessage(Message message, HttpServletRequest request);

    Message findMessageById(Long messageId);
}
