package com.galudisu.handsonic.commons.service;

import com.galudisu.handsonic.commons.domain.IdEntity;
import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseEntityService<M extends BaseEntityMapper<T>, T extends IdEntity> extends BaseService {
    @Autowired
    protected M mapper;

    public Integer insert(T entity) {
        // 生成ID
        entity.setId(generateNextId());

        return this.mapper.insert(entity);
    }

    public Integer update(T entity) {
        return this.mapper.updateById(entity);
    }

    public Integer save(T entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            return update(entity);
        } else {
            return insert(entity);
        }

    }

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }
}
