package com.galudisu.handsonic.core.actor.mapper;

import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import com.galudisu.handsonic.core.actor.domain.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ActorMapper extends BaseEntityMapper<Actor> {
}
