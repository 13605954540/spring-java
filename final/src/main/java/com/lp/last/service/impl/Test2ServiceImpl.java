package com.lp.last.service.impl;

import com.lp.last.bean.Test;
import com.lp.last.bean.Test2;
import com.lp.last.condition.Test2Condition;
import com.lp.last.condition.TestCondition;
import com.lp.last.framework.base.impl.BaseServiceImpl;
import com.lp.last.mapper.Test2Mapper;
import com.lp.last.mapper.TestMapper;
import com.lp.last.service.Test2Service;
import com.lp.last.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test2ServiceImpl extends BaseServiceImpl<Test2Mapper, Test2> implements Test2Service {

    @Autowired
    private Test2Mapper test2Mapper;
}
