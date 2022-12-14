package com.lp.first.framework.lucene.service.impl;

import com.lp.first.framework.bean.BaseEntity;
import com.lp.first.framework.bean.Page;
import com.lp.first.framework.bean.PageParam;
import com.lp.first.framework.lucene.document.DocumentUtils;
import com.lp.first.framework.lucene.service.LuceneService;

import java.util.List;

public class LuceneServiceImpl<T extends BaseEntity> implements LuceneService<T> {

    private DocumentUtils documentUtils;

    public LuceneServiceImpl() {
        documentUtils = new DocumentUtils();
    }

    @Override
    public T selectById(String id) {
        return documentUtils.selectById(id);
    }

    @Override
    public List<T> selectBatchId(List<String> ids) {
        return null;
    }

    @Override
    public long create(T t) {
        return documentUtils.addDocument(t);
    }

    @Override
    public long updateById(T t) {
        return documentUtils.updateById(t);
    }

    @Override
    public long deleteById(String id) {
        return documentUtils.deleteById(id);
    }

    @Override
    public long deleteBatchId(List<String> ids) {
        return documentUtils.deleteById(ids.toString());
    }

    @Override
    public Page<T> selectPage(PageParam<T> pageParam) {
        return null;
    }
}
