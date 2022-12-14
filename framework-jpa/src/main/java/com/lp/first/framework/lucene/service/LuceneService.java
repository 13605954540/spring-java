package com.lp.first.framework.lucene.service;

import com.lp.first.framework.bean.Page;
import com.lp.first.framework.bean.PageParam;

import java.util.List;

public interface LuceneService<T> {

    /**
     * 根据id查询
     *
     * @param id 编号
     * @return
     */
    T selectById(String id);

    /**
     * 根据id集合查询
     *
     * @param ids id集合
     * @return
     */
    List<T> selectBatchId(List<String> ids);

    /**
     * 新增
     *
     * @param t
     * @return
     */
    long create(T t);

    /**
     * 根据id编辑
     *
     * @param t
     * @return
     */
    long updateById(T t);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    long deleteById(String id);

    /**
     * 根据id集合删除
     *
     * @param ids
     */
    long deleteBatchId(List<String> ids);

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    Page<T> selectPage(PageParam<T> pageParam);
}
