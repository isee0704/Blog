package com.shyoc.service;

import com.shyoc.annotation.ClearRedisCache;
import com.shyoc.pojo.Comment;
import com.shyoc.service.impl.UserServiceImpl;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface CommentService {
    /**
     * 根据博客id查询对应的评论数据
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Comment> findCommentListByBlogId(Long id);

    /**
     * 保存评论信息
     * @param comment
     * @param request
     * @return
     */
    @ClearRedisCache(cascade = UserServiceImpl.class)
    int saveComment(Comment comment, HttpServletRequest request);

    Comment findCommentById(Long commentId);
}
