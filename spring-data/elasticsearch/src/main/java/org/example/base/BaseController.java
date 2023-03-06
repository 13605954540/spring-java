package org.example.base;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class BaseController<E extends BaseEntity, C extends BaseService> {

    @Autowired
    protected C service;

    @ApiOperation("新增")
    @PostMapping("/insert")
    protected BaseResp insert(@RequestBody E entity) {
        return ok(service.save(entity));
    }

    @ApiOperation("批量新增")
    @PostMapping("/insertBatch")
    protected BaseResp insertBatch(@RequestBody List<E> entitys) {
        for(E e: entitys) {
            service.save(e);
        }
        return ok();
    }

    @ApiOperation("编辑")
    @PostMapping("/update")
    protected BaseResp update(@RequestBody E entity) {
        return ok(service.updateById(entity));
    }

    @ApiOperation("获取明细")
    @GetMapping("/findById")
    protected BaseResp findById(Long id) {
        return ok(service.findById(id));
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    protected BaseResp removeById(@RequestBody BaseIdReq baseIdReq) {
        service.deleteById(baseIdReq.getId());
        return ok();
    }

    @ApiOperation("根据实体参数查询")
    @PostMapping("/selectByCondition")
    protected BaseResp selectList(@RequestBody E condition) {
//        T t = null;
        return ok(service.find(condition));
//        return ok(service.selectByCondition(condition));
//        return null;
    }

    @ApiOperation("分页查询")
    @PostMapping("/selectPage")
    protected <E extends BaseCondition>Page selectPage(@RequestBody BasePageCondition<E> basePageCondition) {
        return null;
//        return service.selectPage(basePageCondition.getPageParam(), basePageCondition.getCondition());
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
