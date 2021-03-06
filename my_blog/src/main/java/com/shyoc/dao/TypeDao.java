package com.shyoc.dao;

import com.shyoc.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface TypeDao {
    /**
     * 新增分类
     * @param type
     * @return 主键id
     */
    Long saveType(Type type);

    /**
     * 根据id查询一个分类
     * @param id
     * @return Type
     */
    Type findTypeById(Long id);

    /**
     * 根据名称查询分类
     * @param name
     * @return
     */
    Type findTypeByName(String name);

    /**
     * 查询分类列表
     * @return
     */
    List<Type> findTypeList();

    /**
     * 根据id修改分类
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteType(Long id);
}
