package com.lp.last.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lp.last.framework.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "test")
@ApiModel("测试")
@Accessors(chain = true)
public class Test extends BaseEntity {

    private String name;

    private String hobby;
}
