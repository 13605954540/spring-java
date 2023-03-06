package org.example.base;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
        Assert.isNull(p, "Param can not be null");
        this.t = p;
//        this.param = BeanUtils.beanToMap(this.t);
        boolQueryBuilder = new BoolQueryBuilder();
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

    public NativeSearchQuery pageBuild() {
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

    public BoolQueryBuilder build() {
        if(this.t != null) {
            for(String key: this.param.keySet()) {
                boolQueryBuilder.must(QueryBuilders.termQuery(key, this.param.get(key)));
            }
        }
        return boolQueryBuilder;
    }
}
