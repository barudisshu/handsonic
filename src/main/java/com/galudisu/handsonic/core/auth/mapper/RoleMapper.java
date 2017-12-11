package com.galudisu.handsonic.core.auth.mapper;

import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import com.galudisu.handsonic.core.auth.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseEntityMapper<Role> {
}
