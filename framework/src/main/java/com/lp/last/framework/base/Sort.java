package com.lp.last.framework.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("排序类")
@Data
public class Sort implements Serializable {

    private static final long serialVersionUID = -8026677476112750555L;

    @ApiModelProperty("是否升序")
    private Boolean isAsc;

    @ApiModelProperty("排序字段")
    private String property;
}
