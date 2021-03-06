package com.shyoc.service.impl;

import com.shyoc.dao.MessageDao;
import com.shyoc.dao.UserDao;
import com.shyoc.pojo.MailConstants;
import com.shyoc.pojo.MailSendLog;
import com.shyoc.pojo.Message;
import com.shyoc.pojo.User;
import com.shyoc.service.MailSendLogService;
import com.shyoc.service.MessageService;
import com.shyoc.utils.IpAddressUtils;
import com.shyoc.utils.RedisUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${comment.avatar}")
    private String avatar;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MailSendLogService mailSendLogService;

    @Override
    public List<Message> findMessageList() {
        List<Message> messageList = messageDao.findMessageList();
        messageList.forEach(x -> x.getUser());
        List<Message> newMessageList = dealMessageList(messageList);
        return newMessageList;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveMessage(Message message, HttpServletRequest request) {
        message.setCreateTime(System.currentTimeMillis());
//        获取客户端ip地址
        String ipAddress = IpAddressUtils.getIpAddress(request);
//        从请求头中获取token信息
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            User user = (User) redisUtil.get(token);
            if(user != null){
//                token校验通过则为管理员评论
                message.getUser().setId(user.getId());
                message.setAdminMessage(true);
                int saveMessage = messageDao.saveMessage(message);
                long time = System.currentTimeMillis();
                String msgId = String.valueOf(time);
                MailSendLog mailSendLog = new MailSendLog();
                mailSendLog.setMsgId(msgId);
                mailSendLog.setCreateTime(new Date());
                mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
                mailSendLog.setIsComment(false);
                mailSendLog.setCount(0);
                mailSendLog.setNeedId(message.getId());
                mailSendLog.setStatus(MailConstants.DELIVERING);
                mailSendLog.setRouteKey(MailConstants.MESSAGE_MAIL_ROUTING_KEY_NAME);
                mailSendLog.setTryTime(new Date(System.currentTimeMillis()+1000*20*MailConstants.MSG_TIMEOUT));
                mailSendLogService.insert(mailSendLog);
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,"message.key.mail",message,new CorrelationData(msgId));
                return saveMessage;
            }
        }
//        普通用户评论，根据ip地址获取用户信息
        User userByIp = userDao.findUserByIp(ipAddress);
//        判断用户是否存在
        if(userByIp != null){
            message.getUser().setId(userByIp.getId());
        }
        else{
            message.getUser().setAvatar(avatar);
            message.getUser().setIp(ipAddress);
            message.getUser().setType(0);
            message.getUser().setCreateTime(System.currentTimeMillis());
            message.getUser().setUpdateTime(System.currentTimeMillis());
        }
        userDao.saveUser(message.getUser());
        int saveMessage = messageDao.saveMessage(message);

        long time = System.currentTimeMillis();
        String msgId = String.valueOf(time);
        MailSendLog mailSendLog = new MailSendLog();
        mailSendLog.setMsgId(msgId);
        mailSendLog.setCreateTime(new Date());
        mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
        mailSendLog.setIsComment(false);
        mailSendLog.setCount(0);
        mailSendLog.setNeedId(message.getId());
        mailSendLog.setStatus(MailConstants.DELIVERING);
        mailSendLog.setRouteKey(MailConstants.MESSAGE_MAIL_ROUTING_KEY_NAME);
        mailSendLog.setTryTime(new Date(System.currentTimeMillis()+1000*20*MailConstants.MSG_TIMEOUT));
        mailSendLogService.insert(mailSendLog);
        rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,"message.key.mail",message,new CorrelationData(msgId));
        return saveMessage;
    }

    @Override
    public Message findMessageById(Long messageId) {
        return messageDao.findMessageById(messageId);
    }

    /**
     * 找到所有没有父评论的留言
     *
     * @param messageList
     * @return
     */
    public List<Message> dealMessageList(List<Message> messageList) {
        List<Message> newMessageList = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getParentMsgId() == null) {
                newMessageList.add(message);
                List<Message> childList = getChildMessage(message, messageList);
                message.setChildMessages(childList);
                message.setChildMessagesCount(childList.size());
            }
        }
        return newMessageList;
    }

    /**
     * 对顶级留言进行处理，广度优先搜索，逐个加入链表，最后按时间降序排序后返回
     *
     * @param message
     * @param messageList
     * @return
     */
    public List<Message> getChildMessage(Message message, List<Message> messageList) {
        ArrayDeque<Message> queue = new ArrayDeque<>();
        List<Message> childList = new ArrayList<>();
        queue.addLast(message);
        while (!queue.isEmpty()) {
            Message tmpParent = queue.removeFirst();
            for (Message c : messageList) {
                if (c.getParentMsgId() != null && c.getParentMsgId().equals(tmpParent.getId())) {
                    Message tmp = new Message();
                    tmp.setId(tmpParent.getId());
                    tmp.setUser(tmpParent.getUser());
                    c.setParentMessage(tmp);
                    childList.add(c);
                    queue.addLast(c);
                }
            }
        }
        Collections.sort(childList, (a, b) -> (int) (b.getCreateTime() - a.getCreateTime()));
        return childList;
    }
}
