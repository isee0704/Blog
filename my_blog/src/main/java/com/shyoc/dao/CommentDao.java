package com.shyoc.dao;

import com.shyoc.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CommentDao {
    /**
     * 根据博客id查询对应的评论数据
     * @param id
     * @return
     */
    List<Comment> findCommentListByBlogId(Long id);

    /**
     * 保存评论信息
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    Comment findCommentById(Long commentId);
}
