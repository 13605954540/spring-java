package com.lp.last.framework.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseCondition implements Serializable {

    private List<Sort> orders;

    private String id;
}
