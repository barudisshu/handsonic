package com.galudisu.handsonic.core.auth.service;

import com.galudisu.handsonic.commons.service.BaseEntityService;
import com.galudisu.handsonic.core.auth.domain.Role;
import com.galudisu.handsonic.core.auth.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseEntityService<RoleMapper, Role> {
}
