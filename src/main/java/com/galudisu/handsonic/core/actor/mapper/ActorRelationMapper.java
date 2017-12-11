package com.galudisu.handsonic.core.actor.mapper;

import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import com.galudisu.handsonic.core.actor.domain.ActorRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ActorRelationMapper extends BaseEntityMapper<ActorRelation> {
    List<ActorRelation> getRelations(@Param("type") String type, @Param("parentId") long parentId, @Param("childId")
            long childId);

    int delAsChild(@Param("type") String type, @Param("childId") long childId);

    int delAsParent(@Param("type") String type, @Param("parentId") long parentId);
}
