/*
package com.lp.first.learn.mongo;


import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * mongo聚合查询条件构建类
 *
 * @author LP
 * @date 2019/4/5
 *//*

public class MongoDocBuilder {

    private List<Document> aggregateList;

    private Document matchDocument;

    private Document groupDocument;

    private Document sortDocument;

    private Document skipDocument;

    private Document limitDocument;

    private boolean hasMatch;

    private boolean hasGroup;

    private boolean hasSort;

    private boolean hasSkip;

    private boolean hasLimit;

    public MongoDocBuilder match() {
        hasMatch = true;
        return this;
    }

    public MongoDocBuilder and(Document...documents) {
        this.matchDocument.append("$and",documents);
        return this;
    }

    public MongoDocBuilder or(Document...documents) {
        this.matchDocument.append("$or",documents);
        return this;
    }

    public MongoDocBuilder() {
        this.aggregateList = new ArrayList<>();
        this.matchDocument = new Document();
        this.groupDocument = new Document();
        this.limitDocument = new Document();
        this.skipDocument = new Document();
        this.sortDocument = new Document();
    }

    public Document eq(String property,Object value) {
        return append(this.matchDocument,property,"eq",value);
    }

    public Document eq(boolean bool,String property,Object value) {
        return bool ? eq(property,value) : null;
    }

    public Document ne(String property,Object value) {
        return append(this.matchDocument,property,"ne",value);
    }

    public Document ne(boolean bool,String property,Object value) {
        return bool ? ne(property,  value) : null;
    }

    public MongoDocBuilder gt(String property,Object value) {
        this.append(this.matchDocument,property,"gt",value);
        return this;
    }

    public MongoDocBuilder gt(boolean bool,String property,Object value) {
        return bool ? gt(property,  value) : null;
    }

    public Document gte(String property,Object value) {
        return append(this.matchDocument,property,"gte",value);
    }

    public Document gte(boolean bool,String property,Object value) {
        return bool ? gte(property,  value) : null;
    }

    public MongoDocBuilder lt(String property,Object value) {
        this.append(this.matchDocument,property,"lt",value);
        return this;
    }

    public MongoDocBuilder lt(boolean bool,String property,Object value) {
        return bool ? lt(property,  value) : null;
    }

    public Document lte(String property,Object value) {
        return append(this.matchDocument,property,"lte",value);
    }

    public Document lte(boolean bool,String property,Object value) {
        return bool ? lte(property,  value) : null;
    }

    public MongoDocBuilder in(String property,Object...values) {
        this.append(this.matchDocument,property,"in",values);
        return this;
    }

    public MongoDocBuilder in(boolean bool,String property,Object...value) {
        return bool ? in(property,  value) : null;
    }

    public Document notIn(String property,Object...values) {
        return append(this.matchDocument,property,"notIn",values);
    }

    public Document notIn(boolean bool,String property,Object...value) {
        return bool ? notIn(property,value) : null;
    }

    public MongoDocBuilder groupBy(String property) {
        this.groupDocument.append("_id" ,"$" + property);
        hasGroup = true;
        return this;
    }

    public MongoDocBuilder sort(boolean isDesc,String property) {
        this.sortDocument.append(property,isDesc ? -1 : 1);
        hasSort = true;
        return this;
    }

    public MongoDocBuilder skip(Integer currentPage,Integer pageSize) {
        this.skipDocument.append("$skip",(currentPage - 1) * pageSize);
        hasSkip = true;
        return this;
    }

    public MongoDocBuilder skip(boolean bool ,Integer currentPage,Integer pageSize) {
        if(bool) {
            skip(currentPage,pageSize);
        }
        return this;
    }

    public MongoDocBuilder limit(Integer pageSize) {
        this.limitDocument.append("$limit",pageSize);
        hasLimit = true;
        return this;
    }

    public MongoDocBuilder limit(boolean bool,Integer pageSize) {
        if(bool) {
            limit(pageSize);
        }
        return this;
    }

    public MongoDocBuilder sum(String property) {
        this.groupDocument.append(property, new Document("$sum",1));
        return this;
    }

    public MongoDocBuilder sum(boolean isSum , String property) {
        this.groupDocument.append(property, new Document("$sum",property));
        return this;
    }

    public MongoDocBuilder max(String property) {
        this.groupDocument.append("max", new Document("$max","$" + property));
        return this;
    }

    public MongoDocBuilder min(String property) {
        this.groupDocument.append("min", new Document("$max","$" + property));
        return this;
    }

    public MongoDocBuilder avg(String property) {
        this.groupDocument.append("avg", new Document("$avg","$" + property));
        return this;
    }

    public Document append(Document document,String property,String operate,Object...value) {
        return document.append(property,new Document("$" + operate,value));
    }

    public List<Document> build(MongoDocBuilder...document) {
        if(hasMatch) {
            this.aggregateList.add(new Document("$match",matchDocument));
        }
        if(hasGroup) {
            this.aggregateList.add(new Document("$group",groupDocument));
        }
        if(hasSort) {
            this.aggregateList.add(new Document("$sort",sortDocument));
        }
        if(hasSkip) {
            this.aggregateList.add(skipDocument);
        }
        if(hasLimit) {
            this.aggregateList.add(limitDocument);
        }
        return this.aggregateList;
    }
}
*/
