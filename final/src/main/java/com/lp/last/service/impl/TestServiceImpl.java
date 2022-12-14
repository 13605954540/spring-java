package com.lp.last.service.impl;

import com.lp.last.bean.Test;
import com.lp.last.condition.TestCondition;
import com.lp.last.framework.base.impl.BaseServiceImpl;
import com.lp.last.mapper.TestMapper;
import com.lp.last.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends BaseServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private TestMapper testMapper;
}
