package org.example.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class MinimalEntity implements Serializable {

    private static final long serialVersionUID = 1997208262556252784L;

    protected String id;

    protected Date createTime;

    protected Date updateTime;

}
