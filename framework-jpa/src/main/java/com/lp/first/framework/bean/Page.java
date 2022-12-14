package com.lp.first.framework.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("分页返回数据类")
public class Page<T> implements Serializable {

    private Integer current;

    private Integer size;

    private List<T> records;

    private Integer total;

    public static <T> Page<T> of(@Nullable org.springframework.data.domain.Page<T> page) {
        Page<T> result = new Page<T>();
        result.setRecords(page.getContent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotalPages());
        result.setCurrent(page.getNumber() + 1);
        return result;
    }
}
