package org.example.base;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = -8026677476112750059L;

    private Long id;

    private Date updateTime;

    private Date createTime;

    private String createBy;

    private String updateBy;

    private Boolean isDel;

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public T setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return (T)this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public T setCreateTime(Date createTime) {
        this.createTime = createTime;
        return (T)this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public T setCreateBy(String createBy) {
        this.createBy = createBy;
        return (T)this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public T setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return (T)this;
    }

    public Boolean getDel() {
        return isDel;
    }

    public T setDel(Boolean del) {
        isDel = del;
        return (T)this;
    }
}
