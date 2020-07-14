package com.shyoc.service.impl;

import com.shyoc.dao.UserDao;
import com.shyoc.pojo.User;
import com.shyoc.service.UserService;
import com.shyoc.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

    @Override
    public User findAdmin() {
        return userDao.findAdmin();
    }

    @Override
    public User findUserByIp(String ip) {
        return userDao.findUserByIp(ip);
    }

    @Override
    public User findUserByToken(String token) {
        return (User) redisUtil.get(token);
    }

}
