package com.lp.last.framework.base;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author LP
 * @date 2018/5/1
 */
@Data
@ApiModel("主键参数类")
public class BaseIdsReq {

    private List<String> ids;
}
