//package com.lp.first.learn.mongo;
//
///**
// * @author LP
// * @date 2019/4/5
// */
//public class Test {
//
//    public PageReturn<ServiceLogReturn> selectPageGroupByAppId(PageParam pageParam, ServiceLogMongoCondition condition) {
//        MongoCollection<Document> collection = mongoTemplate.getCollection("service_log");
//        List<Document> aggregateList = new ArrayList<Document>();
//        Document document = new Document("start_time",
//                new Document("$gt", condition.getBeginTime().getTime())
//                        .append("$lt", condition.getEndTime().getTime())
//
//        );
//        if (!StringUtils.isEmpty(condition.getServiceId())) {
//            document.append("service_id",
//                    new Document("$eq", condition.getServiceId()));
//        }
//        if (!CollectionUtils.isEmpty(condition.getApplicationIds())) {
//            document.append("application_id",
//                    new Document("$in", condition.getApplicationIds()));
//        }
//        aggregateList.add(new Document("$match", document));
//        aggregateList.add(
//                new Document("$group",
//                        new Document("_id", "$application_id")
//                                .append("count", new Document("$sum", 1))
//                                .append("avg", new Document("$avg", "$elapsed_time"))
//                )
//        );
//        Integer total = Lists.newArrayList(collection.aggregate(aggregateList)).size();
//        aggregateList.add(
//                new Document("$sort",
//                        new Document("count", -1))
//        );
//        if (pageParam != null) {
//            aggregateList.add(
//                    new Document("$skip", (pageParam.getPageIndex() - 1) * pageParam.getPageSize())
//            );
//            aggregateList.add(
//                    new Document("$limit", pageParam.getPageSize())
//            );
//        }
//        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
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
//}
