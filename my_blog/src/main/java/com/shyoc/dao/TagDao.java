package com.shyoc.dao;

import com.shyoc.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface TagDao {
    /**
     * 新增标签
     * @param tag
     * @return 主键id
     */
    Long saveTag(Tag tag);

    /**
     * 根据id查询一个标签
     * @param id
     * @return Tag
     */
    Tag findTagById(Long id);

    /**
     * 根据名称查询标签
     * @param name
     * @return
     */
    Tag findTagByName(String name);

    /**
     * 查询标签列表
     * @return
     */
    List<Tag> findTagList();

    /**
     * 根据id修改标签
     * @param tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 根据id删除标签
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 根据博客id查询标签列表
     * @param id
     * @return
     */
    List<Tag> findTagListByBlogId(@Param("id") Long id);
}
