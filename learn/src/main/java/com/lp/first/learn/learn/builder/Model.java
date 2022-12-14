package com.lp.first.learn.learn.builder;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author LP
 * @date 2019/4/14
 */
@ToString
public class Model implements Serializable {

    private static final long serialVersionUID = 168992000914786964L;

    private String head;

    private String body;

    private String foot;

    public String getHead() {
        return head;
    }

    public Model setHead(String head) {
        this.head = head;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Model setBody(String body) {
        this.body = body;
        return this;
    }

    public String getFoot() {
        return foot;
    }

    public Model setFoot(String foot) {
        this.foot = foot;
        return this;
    }
}
