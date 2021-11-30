package com.ruoyi.test.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            this.strictInsertFill(metaObject, "userId", Long.class, SecurityUtils.getUserId());
            this.strictInsertFill(metaObject, "deptId", Long.class, SecurityUtils.getDeptId());
            this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getUsername());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getUsername());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
