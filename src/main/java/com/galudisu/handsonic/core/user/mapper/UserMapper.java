package com.galudisu.handsonic.core.user.mapper;

import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import com.galudisu.handsonic.core.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseEntityMapper<User> {
}
