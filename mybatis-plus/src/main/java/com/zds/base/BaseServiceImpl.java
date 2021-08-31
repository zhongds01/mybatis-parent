package com.zds.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.Date;

/**
 * @author zhouliang
 * @since 2021-8-31下午 9:32
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseService<T> {

    public BaseServiceImpl() {
    }

    public boolean save(T entity) {
        this.resolveEntity(entity);
        return super.save(entity);
    }

    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        entityList.forEach(this::resolveEntity);
        return super.saveBatch(entityList, batchSize);
    }

    public boolean updateById(T entity) {
        this.resolveEntity(entity);
        return super.updateById(entity);
    }

    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        entityList.forEach(this::resolveEntity);
        return super.updateBatchById(entityList, batchSize);
    }

    public boolean saveOrUpdate(T entity) {
        return entity.getId() == null ? this.save(entity) : this.updateById(entity);
    }

    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        entityList.forEach(this::resolveEntity);
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    private void resolveEntity(T entity) {
        Date now = new Date();
        if (entity.getId() == null) {
            entity.setCreateTime(now);
        }
        entity.setUpdateTime(now);
        entity.setIsDeleted(0);
    }
}
