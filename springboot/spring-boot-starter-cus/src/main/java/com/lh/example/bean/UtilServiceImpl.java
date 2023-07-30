package com.lh.example.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private SimpleBean simpleBean;

    public SimpleBean get() {
        return simpleBean;
    }
}
