package com.lp.last.framework.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lp.last.framework.base.BaseCondition;
import com.lp.last.framework.base.Sort;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class WrapperUtils {

    public static <E extends BaseCondition>Wrapper getWrapper(E condition) {
        Map<String, Object> map = BeanMap.create(condition);
        QueryWrapper wrapper = new QueryWrapper();
        for(String key: map.keySet()) {
            if(key.equals("orders")) continue;
            if(map.get(key) != null) {
                if(key.endsWith("Li")) {
                    Object value = map.get(key);
                    key = key.substring(0, key.length() - 2);
                    wrapper.like(StrTransUtils.camelToUnderline(key), value);
                    continue;
                }
                if(key.endsWith("In")) {
                    List<Object> value = (List<Object>)map.get(key);
                    key = key.substring(0, key.length() - 2);
                    wrapper.in(StrTransUtils.camelToUnderline(key), value);
                    continue;
                }
                if(key.endsWith("Le")) {
                    Object value = map.get(key);
                    key = key.substring(0, key.length() - 2);
                    wrapper.le(StrTransUtils.camelToUnderline(key), value);
                    continue;
                }
                if(key.endsWith("Lt")) {
                    Object value = map.get(key);
                    key = key.substring(0, key.length() - 2);
                    wrapper.lt(StrTransUtils.camelToUnderline(key), value);
                    continue;
                }
                if(key.endsWith("Ge")) {
                    Object value = map.get(key);
                    key = key.substring(0, key.length() - 2);
                    wrapper.ge(StrTransUtils.camelToUnderline(key), value);
                    continue;
                }
                if(key.endsWith("Gt")) {
                    Object value = map.get(key);
                    key = key.substring(0, key.length() - 2);
                    wrapper.gt(StrTransUtils.camelToUnderline(key), value);
                    continue;
                }
                if(key.endsWith("Between")) {
                    List<Object> value = (List<Object>)map.get(key);
                    if(value.size() != 2) {
                        throw new RuntimeException("between操作，请传递长度为2的数组");
                    }
                    key = key.substring(0, key.length() - 6);
                    wrapper.between(StrTransUtils.camelToUnderline(key), value.get(0), value.get(1));
                    continue;
                }
                wrapper.eq(StrTransUtils.camelToUnderline(key), map.get(key));
            }
        }
        List<Sort> orders = condition.getOrders();
        if(!CollectionUtils.isEmpty(orders)) {
            orders.forEach(item -> {
                wrapper.orderBy(Boolean.TRUE, item.getIsAsc(), StrTransUtils.camelToUnderline(item.getProperty()));
            });
        } else {
            wrapper.orderByDesc("create_time");
        }
        return wrapper;
    }
}
