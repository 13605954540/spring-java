package com.lp.first.framework.crud.service;

import com.lp.first.framework.bean.Page;
import com.lp.first.framework.bean.PageParam;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author LP
 * @date 2018/7/23
 */
public interface BaseService<T> {

    T insert(T t);

    T updateById(T t);

    T updateByIdCache(T t);

    T selectById(String id);

    T selectByIdCache(String id);

    void deleteById(String id);

    void deleteByIdCache(String id);

    void deleteBatchIds(Collection<? extends Serializable> idList);

    void deleteBatchIdsCache(Collection<? extends Serializable> idList);

    Page<T> findAll(PageParam<T> pageParam);

    List<T> selectByBatchIds(@Param("ids") Collection<? extends Serializable> ids);

    List<T> saveAll(List<T> list);
}
