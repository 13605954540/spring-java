package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis各类型实操
 * <pre>
 *     string: 字符串，可存数字、对象
 *     map: hash,可存对象，相对于string类型 内存占用大 但是可单独修改对象中的属性
 *     list: 双向链表 可重复  有序   场景: 消息队列（但比不上专业的）
 *     set: 无序集合  不可重复 无序   场景:
 *     zset: 有序集合 不可重复 有序   场景:（延时队列 如订单未支付 延半小时删除）
 * </pre>
 */
@RestController
@RequestMapping("/redis-test")
public class RedisTestController {

    @Autowired
    public RedisTemplate redisTemplate;

    private final static String KEY = "str_key";

    private final static String MAP_KEY = "map_key";

    private final static String LIST_KEY = "list_key";

    private final static String SET_KEY = "set_key";

    private final static String ZSET_KEY = "zset_key";

    /**
     * 字符串类型（可以是数组 对象）
     *
     * @return
     */
    @GetMapping("/str")
    public String str() {
        redisTemplate.opsForValue().set(KEY, "9");
        String str = (String)redisTemplate.opsForValue().get(KEY);
        return str;
    }

    /**
     * map类型
     *
     * @return
     */
    @GetMapping("/map")
    public Object map() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(MAP_KEY, "id", 6);
        hashOperations.put(MAP_KEY, "name", "小强");
        hashOperations.put(MAP_KEY, "name", "小华");
        Object val = hashOperations.get(MAP_KEY, "name");
        return val;
    }

    /**
     * list类型
     *
     * @return
     */
    @GetMapping("/list")
    public List list() {
        ListOperations listOperations = redisTemplate.opsForList();
        redisTemplate.delete(LIST_KEY);
        listOperations.leftPush(LIST_KEY, 0);
        listOperations.rightPush(LIST_KEY, 2);
        listOperations.leftPush(LIST_KEY, 1);
        List<String> list = listOperations.range(LIST_KEY, 0 ,100);
        System.err.println(listOperations.rightPop(LIST_KEY));
        System.err.println(list);
        System.err.println(listOperations.range(LIST_KEY, 0 ,100));
        return list;
    }

    /**
     * set类型
     *
     * @return
     */
    @GetMapping("/set")
    public Set set() {
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(SET_KEY, 0, 1, 2, 3);
        setOperations.add(SET_KEY, 4,5,6,7);
        //拼起来无序
        Set set = setOperations.members(SET_KEY);
        return set;
    }

    /**
     * zset类型
     *
     * @return
     */
    @GetMapping("/zset")
    public Set zset() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        redisTemplate.delete(ZSET_KEY);
        zSetOperations.add(ZSET_KEY, 110, 10);
        zSetOperations.add(ZSET_KEY, 111, 11);
        Set set = zSetOperations.range(ZSET_KEY, 0 ,10);
        return set;
    }

    @GetMapping("/excute")
    public void excute() {
//        String key = "game1";
//        Long currentVolume = redisTemplate.execute((RedisCallback<Long>) connection -> {
//            @SuppressWarnings("unchecked") byte[] rawKey = Optional.ofNullable(
//                    ((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize(key)
//            ).orElseThrow(RuntimeException::new);
//            @SuppressWarnings("unchecked") byte[] rawHashKey = Optional.ofNullable(
//                    ((RedisSerializer<String>) redisTemplate.getHashKeySerializer()).serialize(hashKey)
//            ).orElseThrow(RuntimeException::new);
////            connection.multi();
//            connection.openPipeline();
//            connection.hIncrBy(rawKey, rawHashKey, 1L);
//            connection.expire(rawKey, TimeUnit.SECONDS.convert(1L, TimeUnit.HOURS));
//            List<Object> objects = connection.closePipeline();
////            connection.exec();
//            return objects.stream().findFirst().map(s -> (Long) s).orElseThrow(RuntimeException::new);
//        });
        return;
    }

    @GetMapping("/excuteSession")
    public void excuteSession() {
/*        List<Object> list = redisTemplate.execute((SessionCallback<Object>) connect -> {
            connect.multi();
//            RedisSerializer<String> ser = new StringRedisSerializer();
            RedisSerializer<String> stringSerializer = new StringRedisSerializer();

            connect.opsForValue().set(stringSerializer.serialize("game1"), stringSerializer.serialize("1"));
            connect.opsForValue().set(stringSerializer.serialize("game2"), stringSerializer.serialize("2"));
            return connect.exec();
        });*/

/*        for(Object obj : list) {
            System.err.println(obj);
        }*/
//        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("oneGame", 1);
//        valueOperations.increment("oneGame");
//        ValueOperations<String, Long> valueOps = redisTemplate.opsForValue();
//        valueOps.set("oneGame", 1L);
//        valueOps.increment("oneGame", 1L);
        System.err.println("begin: " + redisTemplate.opsForValue().get("key1"));
        try {
            redisTemplate.executePipelined(new SessionCallback<Object>() {
                @Override
                public Object execute(RedisOperations operations) {
                    operations.multi();
                    operations.opsForValue().set("key50000", 2);
                    operations.opsForValue().increment("key1");
//                    int d = 5 / 0;
                    operations.opsForValue().set("key20", 200);
                    List exec = operations.exec();
                    for(Object obj: exec) {
                        System.err.println("inner: " + obj);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.err.println("key50000: " + redisTemplate.opsForValue().get("key50000"));
            System.err.println("end: " + redisTemplate.opsForValue().get("key1"));
        }
    }

    /**
     * redis消息发送订阅
     * <pre>
     *     无法持久化 建议使用专业的mq
     * </pre>
     */
    @GetMapping("/send")
    public void covert() {
        redisTemplate.convertAndSend("test", "我发送消息了");
    }
}
