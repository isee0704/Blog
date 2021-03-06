package com.shyoc.dao;

import com.shyoc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findUser(@Param("username") String username, @Param("password") String password);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 获取网站管理员用户
     * @return
     */
    User findAdmin();

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    Long saveUser(User user);

    /**
     * 通过ip地址获取用户信息
     * @param ip
     * @return
     */
    User findUserByIp(@Param("ip") String ip);
}
