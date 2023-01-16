package com.lp.last.framework.base.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lp.last.framework.base.BaseCondition;
import com.lp.last.framework.base.BaseEntity;
import com.lp.last.framework.base.BaseService;
import com.lp.last.framework.util.UUIDs;
import com.lp.last.framework.util.WrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected M mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public T insert(T t) {
        if(t.getId() == null) {
            t.setId(UUIDs.get());
        }
        t.setCreateTime(new Date());
        int i = mapper.insert(t);
        return t;
    }

    @Override
    public T updateById(T t) {
        if(StringUtils.isEmpty(t.getId())) {
            throw new RuntimeException("id为空，编辑失败");
        }
        int i = mapper.updateById(t);
        if(i <= 0) {
            throw new RuntimeException("查无编号为" + t.getId() + "的数据，编辑失败");
        }
        return t;
    }

    @Override
    public T selectById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public void deleteById(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void deleteBatchIds(Collection<? extends Serializable> idList) {
        mapper.deleteBatchIds(idList);
    }

    @Override
    public <E extends BaseCondition>Page<T> selectPage(Page<T> pageParam, E condition) {
        return mapper.selectPage(pageParam, WrapperUtils.getWrapper(condition));
    }

    @Override
    public List<T> selectByBatchIds(@Param("ids") Collection<? extends Serializable> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public <E extends BaseCondition>List<T> selectByCondition(E condition) {
        return mapper.selectList(WrapperUtils.getWrapper(condition));
    }

    @Override
    public List<T> saveAll(List<T> list) {
        for(T t: list) {
            insert(t);
        }
        return list;
    }

    @Override
    public List<T> selectList(Wrapper<T> wrapper) {
        return mapper.selectList(wrapper);
    }

    @Override
    public List<T> selectBatchIds(List<String> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public T insertOrUpdate(T t) {
        if(StringUtils.isEmpty(t.getId())) {
            return insert(t);
        }
        return selectById(t.getId()) == null ? insert(t) : updateById(t);
    }

    //缓存 --------------------------------------------------
    @Override
    public T updateByIdCache(T t) {
        redisTemplate.delete(t.getId());
        if(StringUtils.isEmpty(t.getId())) {
            throw new RuntimeException("id为空，编辑失败");
        }
        int i = mapper.updateById(t);
        if(i <= 0) {
            throw new RuntimeException("查无编号为" + t.getId() + "的数据，编辑失败");
        }
        return t;
    }

    @Override
    public T selectByIdCache(String id) {
        T t = (T) redisTemplate.opsForValue().get(id);
        return Optional.ofNullable(t).orElseGet(() -> {
            T r = mapper.selectById(id);
            redisTemplate.opsForValue().set(id, r);
            return r;
        });
    }

    @Override
    public void deleteByIdCache(String id) {
        redisTemplate.delete(id);
        mapper.deleteById(id);
    }

    @Override
    public void deleteBatchIdsCache(Collection<? extends Serializable> idList) {
        redisTemplate.delete((List<String>) idList);
        mapper.deleteBatchIds(idList);
    }
}
