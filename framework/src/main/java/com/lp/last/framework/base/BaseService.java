package com.lp.last.framework.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<T> {

    T insert(T t);

    T updateById(T t);

    T selectById(String id);

    void deleteById(String id);

    void deleteBatchIds(Collection<? extends Serializable> idList);

    <E extends BaseCondition>Page<T> selectPage(Page<T> pageParam, E condition);

    List<T> selectByBatchIds(@Param("ids") Collection<? extends Serializable> ids);

    List<T> saveAll(List<T> list);

    <E extends BaseCondition>List<T> selectByCondition(E condition);

    List<T> selectList(Wrapper<T> wrapper);

    List<T> selectBatchIds(List<String> ids);

    T insertOrUpdate(T t);

    //---------------缓存-----------------------
    T updateByIdCache(T t);

    T selectByIdCache(String id);

    void deleteByIdCache(String id);

    void deleteBatchIdsCache(Collection<? extends Serializable> idList);
}
