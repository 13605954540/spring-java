package com.lp.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel("排序类")
@Data
@Accessors(chain = true)
public class Sort implements Serializable {

    private static final long serialVersionUID = -8026677476112750555L;

    @ApiModelProperty("是否升序")
    private Boolean isAsc;

    @ApiModelProperty("排序字段")
    private String property;
}
