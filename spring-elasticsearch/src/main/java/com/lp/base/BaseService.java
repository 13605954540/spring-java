package com.lp.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<T extends BaseEntity, E extends BaseCondition> {

    /**
     * 新增或修改
     *
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 批量新增
     *
     * @param ts
     * @return
     */
    List<T> saveAll(List<T> ts);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    T findById(Serializable id);

    /**
     * 根据实体类条件查询
     *
     * @param t
     * @return
     */
    Collection<T> find(T t);

    /**
     * 编辑
     *
     * @param t
     * @return
     */
    T updateById(T t);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * 根据实体参数删除
     *
     * @param t
     */
    void delete(T t);

    /**
     * 根据id集合删除
     *
     * @param ids
     */
    void deleteBatchIds(List<Serializable> ids);

    /**
     * 根据多个实体参数删除
     *
     * @param ts
     */
    void deleteBatch(List<T> ts);

    /**
     * 分页查询
     *
     * @param t 实体参数
     * @param pageable 分页参数
     * @return
     */
    Page<T> selectPage(T t, Pageable pageable);
}
