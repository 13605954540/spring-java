package com.lp.last.framework.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lp.last.framework.util.StringUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 林苹
 * @date 2022/12/01
 */
@Component
public class MetaObjectHandlerImpl implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("isDel", Boolean.FALSE, metaObject);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !StringUtil.isEmpty(authentication.getName())) {
            this.setFieldValByName("createBy", authentication.getName(), metaObject);
            this.setFieldValByName("updateBy", authentication.getName(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !StringUtil.isEmpty(authentication.getName())) {
            this.setFieldValByName("updateBy", authentication.getName(), metaObject);
        }
    }
}
