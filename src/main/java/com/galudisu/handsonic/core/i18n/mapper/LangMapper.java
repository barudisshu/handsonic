package com.galudisu.handsonic.core.i18n.mapper;

import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import com.galudisu.handsonic.core.i18n.domain.Lang;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LangMapper extends BaseEntityMapper<Lang> {
}
