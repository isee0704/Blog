package com.shyoc.service;

import com.github.pagehelper.PageInfo;
import com.shyoc.annotation.ClearRedisCache;
import com.shyoc.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface TagService {
    /**
     * 新增分类
     * @param tag
     * @return 主键id
     */
    @ClearRedisCache
    Long saveTag(Tag tag);

    /**
     * 根据id查询一个分类
     * @param id
     * @return Tag
     */
    @Cacheable(cacheNames = "cache")
    Tag findTagById(Long id);

    /**
     * 根据名称查询分类
     * @param name
     * @return
     */
    @Cacheable(cacheNames = "cache")
    Tag findTagByName(String name);

    /**
     *查询标签列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Tag> findTagList(Integer currentPage, Integer pageSize);

    /**
     * 查询标签列表前top条数据
     * @param top
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Tag> findTopTagList(@Param("top") Integer top);

    /**
     * 修改分类
     * @param tag
     * @return
     */
    @ClearRedisCache
    int updateTag(Tag tag);

    /**
     * 根据id删除分类
     * @param id
     */
    @ClearRedisCache
    void deleteTag(Long id);
}
