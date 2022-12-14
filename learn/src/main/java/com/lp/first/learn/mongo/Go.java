/*
package com.lp.first.learn.mongo;

import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author LP
 * @date 2019/4/5
 *//*

public class Go {

    private static Document document;

    private static Integer i1;

    private static int i;

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
//        List<Test> test = mongoTemplate.aggregate(agg,"service_log",Test.class).getMappedResults();

//        System.err.println(document);
//        System.err.println(i1);
//        System.err.println(i);
//        List<Document> params = MongoUtil.init()
//                .eq(true,"_test",1)
//                .eq(true,"_app",2)
//                .groupBy("_id")
//                .sort(true,"_sort")
//                .skip(2,10)
//                .limit(10)
//                .build();
    //    public PageReturn<ServiceLogReturn> selectPageGroupByAppId(PageParam pageParam, ServiceLogMongoCondition condition) {
//        MongoCollection<Document> collection = mongoTemplate.getCollection("service_log");
        List<Document> aggregateList = new ArrayList<Document>();
        Document document = new Document("start_time",
                new Document("$gt", 123)
                        .append("$lt", 456)

        );
            document.append("service_id",
                    new Document("$eq", "789"));
            document.append("application_id",
                    new Document("$in", "135"));
        aggregateList.add(new Document("$match", document));
        aggregateList.add(
                new Document("$group",
                        new Document("_id", "$application_id")
                                .append("count", new Document("$sum", 1))
                                .append("avg", new Document("$avg", "$elapsed_time"))
                )
        );
//        Integer total = Lists.newArrayList(collection.aggregate(aggregateList)).size();
        aggregateList.add(
                new Document("$sort",
                        new Document("count", -1))
        );

            aggregateList.add(
                    new Document("$skip", 10)
            );
            aggregateList.add(
                    new Document("$limit", 10)
            );

            System.err.println(aggregateList);
        AggregateIterable<Document> resultset = mongoTemplate.getCollection("123").aggregate(aggregateList);
//        MongoCursor<Document> cursor = resultset.iterator();
//
//        ServiceLogReturn serviceLogReturn = null;
//        List<ServiceLogReturn> list = new ArrayList<>();
//        try {
//            while (cursor.hasNext()) {
//                serviceLogReturn = new ServiceLogReturn();
//                Document item = cursor.next();
//                String id = item.getString("_id");
//                Long count = Long.valueOf(item.getInteger("count", 0));
//                Double avg = item.getDouble("avg");
//                serviceLogReturn.setApplicationId(id);
//                serviceLogReturn.setAvgElapsedTime(avg.intValue());
//                serviceLogReturn.setInvokingCount(count.intValue());
//                list.add(serviceLogReturn);
//            }
//        } finally {
//            cursor.close();
//        }
//        return PageReturn.of(total, list);
//    }
    }
}
*/
