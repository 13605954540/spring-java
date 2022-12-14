package com.lp.first.learn.learn.prototype;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author LP
 * @date 2019/4/13
 */
@ToString
public class Prototype implements Cloneable {

    private String name;

    private int inLow;

    private Integer inHigh;

    private List<String> list;

    private Map<String,Object> map;

    private Model model;

    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }

    public String getName() {
        return name;
    }

    public Prototype setName(String name) {
        this.name = name;
        return this;
    }

    public int getInLow() {
        return inLow;
    }

    public Prototype setInLow(int inLow) {
        this.inLow = inLow;
        return this;
    }

    public Integer getInHigh() {
        return inHigh;
    }

    public Prototype setInHigh(Integer inHigh) {
        this.inHigh = inHigh;
        return this;
    }

    public List<String> getList() {
        return list;
    }

    public Prototype setList(List<String> list) {
        this.list = list;
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Prototype setMap(Map<String, Object> map) {
        this.map = map;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Prototype setModel(Model model) {
        this.model = model;
        return this;
    }
}
