package com.shyoc.service;

import com.shyoc.annotation.ClearRedisCache;
import com.shyoc.pojo.Link;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface LinkService {
    /**
     * 查找友情链接列表
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Link> findLinkList();

    /**
     * 查找已允许的友情链接列表
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Link> findAllowedLinkList();

    /**
     * 保存友情链接
     * @param link
     * @return
     */
    @ClearRedisCache
    Integer saveLink(Link link);

    /**
     * 更新友情链接
     * @param link
     * @return
     */
    @ClearRedisCache
    Integer updateLink(Link link);

    /**
     * 根据id删除友情链接
     * @param id
     */
    @ClearRedisCache
    void deleteLink(Long id);
}
