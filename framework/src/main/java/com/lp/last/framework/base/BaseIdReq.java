package com.lp.last.framework.base;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author LP
 * @date 2018/5/1
 */
@Data
@ApiModel("主键参数类")
public class BaseIdReq {

    private String id;
}
