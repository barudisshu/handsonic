package com.galudisu.handsonic.core.department.mapper;

import com.galudisu.handsonic.commons.persistence.mybatis.BaseEntityMapper;
import com.galudisu.handsonic.commons.service.BaseEntityService;
import com.galudisu.handsonic.core.department.domain.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface DepartmentMapper extends BaseEntityMapper<Department> {
    @Service
    class DepartmentService extends BaseEntityService<DepartmentMapper, Department> {
    }
}
