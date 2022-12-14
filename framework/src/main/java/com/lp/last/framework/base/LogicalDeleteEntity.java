package com.lp.last.framework.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LogicalDeleteEntity extends MinimalEntity {
    private static final long serialVersionUID = 1063802602941064760L;

    @TableField("is_del")
    @TableLogic
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected Boolean isDel;
}
