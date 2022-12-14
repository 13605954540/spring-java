package com.lp.first.framework.lucene.document;

import com.lp.first.framework.bean.BaseEntity;
import com.lp.first.framework.lucene.index.LuceneIndex;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

public class DocumentUtils {

    private LuceneIndex luceneIndex;

    private volatile static DocumentUtils documentUtils = null;

    public DocumentUtils() {
        luceneIndex = new LuceneIndex(null, null, null, null);
    }

    public <T> long addDocument (T t) {
        IndexWriter writer = luceneIndex.getIndexWriter();
        long res = 0L;
        try {
            res = writer.addDocument(LDocument.toDoc(t));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            luceneIndex.close();
        }
        return res;
    }

    public <T extends BaseEntity> long updateById(T t) {
        IndexWriter writer = luceneIndex.getIndexWriter();
        long res = 0L;
        try {
            res = writer.updateDocument(new Term("id",t.getId()), LDocument.toDoc(t));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            luceneIndex.close();
        }
        return res;
    }

    public <T> T selectById(String id) {
        IndexReader reader = luceneIndex.getIndexReader();
        IndexSearcher searcher = new IndexSearcher(reader);
        try {
            TopDocs topDocs = searcher.search(new TermQuery(new Term("id", id)), 2);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            if(scoreDocs.length > 1) {
                throw new RuntimeException("数量超过1!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            luceneIndex.close();
        }
        return null;
    }

    public long deleteById(String id) {
        IndexWriter writer = luceneIndex.getIndexWriter();
        long res = 0L;
        try {
            res = writer.deleteDocuments(new Term("id", id));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            luceneIndex.close();
        }
        return res;
    }

    public long delete(Term[] terms) {
        IndexWriter writer = luceneIndex.getIndexWriter();
        long res = 0L;
        try {
            res = writer.deleteDocuments(terms);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            luceneIndex.close();
        }
        return res;
    }

    //单例模式
    public static DocumentUtils getInstance() {
        if(documentUtils == null) {
            synchronized (DocumentUtils.class) {
                if(documentUtils == null) {
                    documentUtils = new DocumentUtils();
                    return documentUtils;
                }
            }
        }
        return documentUtils;
    }
}
