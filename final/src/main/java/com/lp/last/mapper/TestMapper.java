package com.lp.last.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lp.last.bean.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
