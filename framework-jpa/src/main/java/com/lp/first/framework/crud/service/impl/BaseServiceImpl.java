package com.lp.first.framework.crud.service.impl;

import com.lp.first.framework.bean.BaseEntity;
import com.lp.first.framework.bean.Page;
import com.lp.first.framework.bean.PageParam;
import com.lp.first.framework.crud.dao.BaseDao;
import com.lp.first.framework.crud.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author LP
 * @date 2018/7/23
 */
@Transactional
public abstract class BaseServiceImpl<M extends BaseDao<T, String>, T extends BaseEntity> implements BaseService<T> {

    public abstract M getDao();

    public abstract Specification<T> getSpecification(T t);

    public abstract Sort getSort();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public T insert(T t) {
        return getDao().save(t);
    }

    @Override
    public T updateById(T t) {
        return getDao().save(t);
    }

    @Override
    public T updateByIdCache(T t) {
        redisTemplate.delete(t.getId());
        return getDao().save(t);
    }

    @Override
    public T selectById(String id) {
        Optional<T> optional =  getDao().findById(id);
        return optional.orElse(null);
    }

    @Override
    public T selectByIdCache(String id) {
        T t = (T) redisTemplate.opsForValue().get(id);
        T result = null;
        if (t != null) {
            result = t;
        } else {
            Optional<T> optional = getDao().findById(id);
            result = optional.orElse(null);
            redisTemplate.opsForValue().set(String.valueOf(id), result);
        }
        return result;
    }

    @Override
    public void deleteById(String id) {
        getDao().deleteById(id);
    }

    @Override
    public void deleteByIdCache(String id) {
        redisTemplate.delete(id);
        getDao().deleteById(id);
    }

    @Override
    public void deleteBatchIds(Collection<? extends Serializable> idList) {
        getDao().deleteBatchIds(idList);
    }

    @Override
    public void deleteBatchIdsCache(Collection<? extends Serializable> idList) {
        redisTemplate.delete((List<String>) idList);
        getDao().deleteBatchIds(idList);
    }

    @Override
    public Page<T> findAll(PageParam<T> pageParam) {
        Pageable pageable = PageRequest.of(pageParam.getCurrent() - 1, pageParam.getSize(), getSort() == null ?
                Sort.by(Sort.Direction.DESC, "createTime") :
                getSort());
        Specification<T> specification = getSpecification(pageParam.getParam());
        return Page.of(getDao().findAll(specification, pageable));
    }

    @Override
    public List<T> selectByBatchIds(@Param("ids") Collection<? extends Serializable> ids) {
        return getDao().selectByBatchIds(ids);
    }

    public List<T> saveAll(List<T> list) {
        return getDao().saveAll(list);
    }
}
