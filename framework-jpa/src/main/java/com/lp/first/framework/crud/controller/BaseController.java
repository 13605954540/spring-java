package com.lp.first.framework.crud.controller;

import com.lp.first.framework.bean.BaseRqBean;
import com.lp.first.framework.bean.ListParam;
import com.lp.first.framework.bean.Page;
import com.lp.first.framework.bean.PageParam;
import com.lp.first.framework.crud.service.BaseService;
import com.lp.first.framework.system.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LP
 * @date 2018/5/1
 */
@RestController
public abstract class BaseController<T> {

    @Autowired
    public abstract BaseService<T> getProvider();

    /**
     * 通用新增
     *
     * @param t
     * @return
     */
    @PostMapping("/insert")
    public BaseResp insert(@RequestBody T t) {
        return BaseResp.ok(getProvider().insert(t));
    }

    /**
     * 通用根据id删除
     *
     * @param baseRqBean 实体基类
     * @return
     */
    @PostMapping("/deleteById")
    public BaseResp deleteById(@RequestBody BaseRqBean baseRqBean) {
        getProvider().deleteById(baseRqBean.getId());
        return BaseResp.ok();
    }

    /**
     * 通用根据id删除(redis)
     *
     * @param baseRqBean 实体基类
     * @return
     */
    @PostMapping("/deleteByIdCache")
    public BaseResp deleteByIdCache(@RequestBody BaseRqBean baseRqBean) {
        getProvider().deleteByIdCache(baseRqBean.getId());
        return BaseResp.ok();
    }

    /**
     * 通用根据多个id删除
     *
     * @param baseRqBean 实体基类
     * @return
     */
    @PostMapping("/deleteBatchIds")
    public BaseResp deleteBatchIds(@RequestBody BaseRqBean baseRqBean) {
        getProvider().deleteBatchIds(baseRqBean.getIds());
        return BaseResp.ok();
    }

    /**
     * 通用根据多个id删除(redis)
     *
     * @param baseRqBean 实体基类
     * @return
     */
    @PostMapping("/deleteByIdsCache")
    public BaseResp deleteBatchIdsCache(@RequestBody BaseRqBean baseRqBean) {
        getProvider().deleteBatchIdsCache(baseRqBean.getIds());
        return BaseResp.ok();
    }

    /**
     * 根据id编辑数据(判空)
     *
     * @param t
     * @return
     */
    @PostMapping("/updateById")
    public BaseResp updateById(@RequestBody T t) {
        return BaseResp.ok(getProvider().updateById(t));
    }

    /**
     * 根据id编辑数据(判空)(redis)
     *
     * @param t
     * @return
     */
    @PostMapping("/updateByIdCache")
    public BaseResp updateByIdCache(@RequestBody T t) {
        return BaseResp.ok(getProvider().updateByIdCache(t));
    }

    /**
     * 根据id查询
     *
     * @param baseRqBean 实体基类
     * @return
     */
    @PostMapping("/selectById")
    public BaseResp selectById(@RequestBody BaseRqBean baseRqBean) {
        T t = getProvider().selectById(baseRqBean.getId());
        return BaseResp.ok(t);
    }

    /**
     * 根据id查询(redis)
     *
     * @param baseRqBean 实体基类
     * @return
     */
    @PostMapping("/selectByIdCache")
    public BaseResp selectByIdCache(@RequestBody BaseRqBean baseRqBean) {
        T t = getProvider().selectByIdCache(baseRqBean.getId());
        return BaseResp.ok(t);
    }

    /**
     * 单表分页
     *
     * @param pageParam 分页相关参数
     * @return
     */
    @PostMapping("/selectPage")
    public BaseResp selectPage(@RequestBody PageParam<T> pageParam) {
        Page page = getProvider().findAll(pageParam);
        return BaseResp.ok(page);
    }

    /**
     * 批量查询
     *
     * @param baseRqBean id集合
     * @return
     */
    @PostMapping("/selectByBatchIds")
    public BaseResp<T> selectByBatchIds(@RequestBody BaseRqBean baseRqBean) {
        return BaseResp.ok(getProvider().selectByBatchIds(baseRqBean.getIds()));
    }

    /**
     * 批量新增 or 编辑
     *
     * @param listParam 实体类参数集合
     * @return
     */
    @PostMapping("/saveAll")
    public BaseResp<T> saveAll(@RequestBody ListParam<T> listParam) {
        List<T> list = getProvider().saveAll(listParam.getList());
        return BaseResp.ok(list);
    }
}
