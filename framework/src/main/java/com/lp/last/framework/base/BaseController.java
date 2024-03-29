package com.lp.last.framework.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class BaseController<E extends MinimalEntity, C extends BaseService> {

    @Autowired
    protected C service;

    @ApiOperation("新增")
    @PostMapping("/insert")
    private BaseResp insert(@RequestBody E entity) {
        return ok(service.insert(entity));
    }

    @ApiOperation("批量新增")
    @PostMapping("/insertBatch")
    private BaseResp insertBatch(@RequestBody List<E> entitys) {
        for(E e: entitys) {
            service.insert(e);
        }
        return ok();
    }

    @ApiOperation("编辑")
    @PostMapping("/update")
    private BaseResp update(@RequestBody E entity) {
        return ok(service.updateById(entity));
    }

    @ApiOperation("获取明细")
    @GetMapping("/detail")
    private BaseResp selectById(String id) {
        return ok(service.selectById(id));
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    private BaseResp removeById(@RequestBody BaseIdReq baseIdReq) {
        service.deleteById(baseIdReq.getId());
        return ok();
    }

    @ApiOperation("根据实体参数查询")
    @PostMapping("/selectByCondition")
    private <E extends BaseCondition>BaseResp selectList(@RequestBody E condition) {
        return ok(service.selectByCondition(condition));
    }

    @ApiOperation("分页查询")
    @PostMapping("/selectPage")
    private <E extends BaseCondition>Page selectPage(@RequestBody BasePageCondition<E> basePageCondition) {
        return service.selectPage(basePageCondition.getPageParam(), basePageCondition.getCondition());
    }

    protected <T>BaseResp<T> ok() {
        return BaseResp.ok();
    }

    protected <T>BaseResp<T> ok(T t) {
        return BaseResp.ok(t);
    }

    protected BaseResp of(String message) {
        return BaseResp.of(message);
    }
}
