package com.shyoc.dao;

import com.shyoc.pojo.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BlogTagDao {
    /**
     * 通过标签Id查找博客Id列表
     * @param tagId
     * @return
     */
    List<BlogTag> findBlogTagByTagId(@Param("tagId") Long tagId);

    /**
     * 通过博客Id查找标签Id列表
     * @param blogId
     * @return
     */
    List<BlogTag> findBlogTagByBlogId(@Param("blogId") Long blogId);

    /**
     * 通过标签Id查找该标签对应的博客数量
     * @param tagId
     * @return
     */
    Integer findCountByTagId(@Param("tagId") Long tagId);
}
