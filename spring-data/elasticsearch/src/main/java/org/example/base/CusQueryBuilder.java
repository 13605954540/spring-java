package org.example.base;

import com.google.common.collect.Maps;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CusQueryBuilder<T> {

    private BoolQueryBuilder boolQueryBuilder;

    private List<SortBuilder> sortBuilders = new ArrayList();

    private Pageable page;

    private T t;

    private Map<String, Object> param;

    public CusQueryBuilder() {
        boolQueryBuilder = new BoolQueryBuilder();
    }

    public CusQueryBuilder(T p) {
        verifyAndParse(p);
    }

    /**
     * 校验，并把实体类非空参数转换查询参数
     *
     * @param p
     */
    private void verifyAndParse(T p) {
        Assert.notNull(p, "Param can not be null");
        this.t = p;
        this.param = Maps.newHashMap();
        BeanMap beanMap = BeanMap.create(p);
        for (Object key : beanMap.keySet()) {
            Object value = beanMap.get(key);
            if(value == null) {
                continue;
            }
            this.param.put(key + "", value);
        }
        boolQueryBuilder = new BoolQueryBuilder();
    }

    public CusQueryBuilder(T p, Pageable pageable) {
        verifyAndParse(p);
        this.page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
    }

    public CusQueryBuilder<T> eq(String key, Serializable value) {
        boolQueryBuilder.must(QueryBuilders.termQuery(key,value));
        return this;
    }

    public CusQueryBuilder<T> ne(String key, Serializable value) {
        boolQueryBuilder.mustNot(QueryBuilders.termQuery(key,value));
        return this;
    }

    public CusQueryBuilder<T> like(String key, Serializable value) {
        boolQueryBuilder.must(QueryBuilders.matchQuery(key,value));
        return this;
    }

    public CusQueryBuilder<T> notLike(String key, Serializable value) {
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery(key,value));
        return this;
    }

    public CusQueryBuilder<T> ge(String key, Serializable value) {
        boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gte(value));
        return this;
    }

    public CusQueryBuilder<T> gt(String key, Serializable value) {
        boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gt(value));
        return this;
    }

    public CusQueryBuilder<T> le(String key, Serializable value) {
        boolQueryBuilder.must(QueryBuilders.rangeQuery(key).lte(value));
        return this;
    }

    public CusQueryBuilder<T> lt(String key, Serializable value) {
        boolQueryBuilder.must(QueryBuilders.rangeQuery(key).lt(value));
        return this;
    }



    public CusQueryBuilder<T> eq(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.must(QueryBuilders.termQuery(key, value));
        }
        return this;
    }

    public CusQueryBuilder<T> ne(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.mustNot(QueryBuilders.termQuery(key, value));
        }
        return this;
    }

    public CusQueryBuilder<T> like(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(key, value));
        }
        return this;
    }

    public CusQueryBuilder<T> notLike(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery(key, value));
        }
        return this;
    }

    public CusQueryBuilder<T> ge(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gte(value));
        }
        return this;
    }

    public CusQueryBuilder<T> gt(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gt(value));
        }
        return this;
    }

    public CusQueryBuilder<T> le(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(key).lte(value));
        }
        return this;
    }

    public CusQueryBuilder<T> lt(boolean isTrue, String key, Serializable value) {
        if(isTrue) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(key).lt(value));
        }
        return this;
    }

    public CusQueryBuilder<T> orderByDesc(String key) {
        SortBuilder sortBuilder = SortBuilders.fieldSort(key).order(SortOrder.DESC);
        sortBuilders.add(sortBuilder);
        return this;
    }

    public CusQueryBuilder<T> orderByAsc(String key) {
        SortBuilder sortBuilder = SortBuilders.fieldSort(key).order(SortOrder.ASC);
        sortBuilders.add(sortBuilder);
        return this;
    }

    public CusQueryBuilder<T> orderByDesc(boolean isTrue, String key) {
        if(isTrue) {
            SortBuilder sortBuilder = SortBuilders.fieldSort(key).order(SortOrder.DESC);
            sortBuilders.add(sortBuilder);
        }
        return this;
    }

    public CusQueryBuilder<T> orderByAsc(boolean isTrue, String key) {
        if (isTrue) {
            SortBuilder sortBuilder = SortBuilders.fieldSort(key).order(SortOrder.ASC);
            sortBuilders.add(sortBuilder);
        }
        return this;
    }

    public CusQueryBuilder<T> orderBy(String key, SortOrder sortOrder) {
        SortBuilder sortBuilder = SortBuilders.fieldSort(key).order(sortOrder);
        sortBuilders.add(sortBuilder);
        return this;
    }

    public CusQueryBuilder<T> orderBy(boolean isTrue, String key, SortOrder sortOrder) {
        if(isTrue) {
            SortBuilder sortBuilder = SortBuilders.fieldSort(key).order(sortOrder);
            sortBuilders.add(sortBuilder);
        }
        return this;
    }

    public CusQueryBuilder<T> limit(int page, int pageSize) {
        this.page = PageRequest.of(page - 1, pageSize);
        return this;
    }

    //分页
    public NativeSearchQuery buildPage() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        if(this.t != null) {
            for(String key: this.param.keySet()) {
                boolQueryBuilder.must(QueryBuilders.termQuery(key, this.param.get(key)));
            }
        }
        if(this.page != null) {
            queryBuilder.withPageable(this.page);
        }
        if(!CollectionUtils.isEmpty(sortBuilders)) {
            sortBuilders.forEach(item -> queryBuilder.withSort(item));
        }
        queryBuilder.withQuery(boolQueryBuilder);
        return queryBuilder.build();
    }

    public Query build() {
        if(this.t != null) {
            for(String key: this.param.keySet()) {
                boolQueryBuilder.must(QueryBuilders.termQuery(key, this.param.get(key)));
            }
        }
        return new NativeSearchQuery(boolQueryBuilder);
    }
}
