package com.lp.first.framework.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Component
public interface BaseDao<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>{

    @Modifying
    @Query(value = "update #{#entityName} set is_del = 1 where id = :id")
    void deleteById(@Param("id") ID id);

    @Modifying
    @Query(value = "update #{#entityName} set is_del = 1 where id in (:ids)")
    void deleteBatchIds(@Param("ids") Collection<? extends Serializable> ids);

    @Query(value = "from #{#entityName} where id in (:ids)")
    List<T> selectByBatchIds(@Param("ids") Collection<? extends Serializable> ids);
}
