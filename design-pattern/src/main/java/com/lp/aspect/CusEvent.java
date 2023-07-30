package com.lp.aspect;

import org.springframework.context.ApplicationEvent;

/**
 *
 */
public class CusEvent extends ApplicationEvent {

    public CusEvent(Object source) {
        super(source);
    }
}
