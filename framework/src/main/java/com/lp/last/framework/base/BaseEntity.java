package com.lp.last.framework.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity extends LogicalDeleteEntity implements Serializable {
    private static final long serialVersionUID = -8026677476112750059L;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    public String getcreateBy() {
        return createBy;
    }

    public BaseEntity setcreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getupdateBy() {
        return updateBy;
    }

    public BaseEntity setupdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @Override
    public BaseEntity setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public BaseEntity setIsDel(Boolean isDel) {
        super.setIsDel(isDel);
        return this;
    }

    @Override
    public BaseEntity setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
        return this;
    }

    @Override
    public BaseEntity setUpdateTime(Date updateTime) {
        super.setUpdateTime(updateTime);
        return this;
    }

}
