package org.example.base.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.example.base.BaseCondition;
import org.example.base.BaseEntity;
import org.example.base.BaseService;
import org.example.base.CusQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseServiceImpl<T extends BaseEntity,E extends BaseCondition, R extends ElasticsearchRepository<T, Serializable>> implements BaseService<T, E> {

    @Autowired
    private R r;

    @Autowired
    public ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public T save(T t) {
        return r.save(t);
    }

    @Override
    public List<T> saveAll(List<T> ts) {
        return (List<T>)r.saveAll(ts);
    }

    @Override
    public T findById(Serializable id) {
        Assert.notNull(id, "Ids must not be null！");
        Optional<T> optional = r.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<T> find(T t) {
        return ((SearchHits<T>)elasticsearchRestTemplate.search(
                new CusQueryBuilder<T>(t).build(),
                t.getClass())
        ).getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public T updateById(T t) {
        Optional.ofNullable(t)
                .map(BaseEntity::getId)
                .orElseThrow(() -> new RuntimeException("Id must not be null！"));
        return r.save(t);
    }

    @Override
    public void deleteById(Serializable id) {
        Assert.notNull(id, "Id must not be null！");
        r.deleteById(id);
    }

    @Override
    public void delete(T t) {
        r.delete(t);
    }

    @Override
    public void deleteBatchIds(List<Serializable> ids) {
        Assert.notEmpty(ids, "Ids must not be null！");
        r.deleteAllById(ids);
    }

    @Override
    public void deleteBatch(List<T> ts) {
        r.deleteAll(ts);
    }

    @Override
    public Page<T> selectPage(T t, Pageable pageable) {
        SearchHits<T> searchHits = ((SearchHits<T>)elasticsearchRestTemplate.search(
                new CusQueryBuilder<T>(t, pageable).buildPage(), t.getClass())
        );
        List<T> records = searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return new PageImpl(records, pageable, searchHits.getTotalHits());
    }
}
