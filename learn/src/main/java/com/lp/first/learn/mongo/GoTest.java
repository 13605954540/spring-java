/*
package com.lp.first.learn.mongo;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

*/
/**
 * @author LP
 * @date 2019/4/5
 *//*

public class GoTest {

    private static MongoTemplate mongoTemplate;

    public static void main(String[] args) {
//        Document
//        Aggregation agg = Aggregation.newAggregation(
//                Aggregation.match(new Criteria()
//                        .andOperator(Criteria.where("onlineTime").gt(new Date()))
//                        .orOperator( Criteria.where("offlineTime").gt(new Date()),Criteria.where("offlineTime").exists(false))),
//                Aggregation.group("start_time").count().as("test"),
//                Aggregation.sort(Sort.Direction.DESC,"test"),
//                Aggregation.skip((2 - 1) * 10L),
//                Aggregation.limit(2 * 10L)
//        );
//        List<Test> test = mongoTemplate.aggregate(agg,"service_log",Test.class).getMappedResults();\
        MongoDocBuilder mgo = new MongoDocBuilder();
        List<Document> list = mgo.build(
                mgo.match().gt("start_time","123").lt("service_id","789").in("application_id", "135"),
                mgo.groupBy("application_id").sum("count").avg("elapsed_time"),
                mgo.sort(true,"count"),
                mgo.skip(2,10),
                mgo.limit(10)
        );
        System.err.println(list);
    }
}
*/
