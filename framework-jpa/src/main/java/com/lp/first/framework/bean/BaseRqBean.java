package com.lp.first.framework.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Collection;

/**
 * @author LP
 * @date 2018/5/1
 */
@Data
@ApiModel("主键参数类")
public class BaseRqBean {

    private String id;

    private Collection<String> ids;
}
