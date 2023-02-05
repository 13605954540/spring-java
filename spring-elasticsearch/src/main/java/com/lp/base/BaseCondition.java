package com.lp.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class BaseCondition implements Serializable {

    private List<Sort> orders;

    private String id;
}
