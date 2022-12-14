package com.lp.last.framework.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class MinimalEntity implements Serializable {

    private static final long serialVersionUID = 1997208262556252784L;

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    protected String id;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

}
