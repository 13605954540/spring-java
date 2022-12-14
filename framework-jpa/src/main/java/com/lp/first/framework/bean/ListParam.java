package com.lp.first.framework.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ListParam<T> implements Serializable {

    private List<T> list;
}
