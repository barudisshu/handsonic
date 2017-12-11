package com.galudisu.handsonic.commons.persistence.mybatis;

import com.galudisu.handsonic.commons.domain.IdEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface BaseEntityMapper<T extends IdEntity> extends BaseMapper<T> {
}
