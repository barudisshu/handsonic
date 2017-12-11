package com.galudisu.handsonic.core.auth.service;

import com.galudisu.handsonic.commons.service.BaseEntityService;
import com.galudisu.handsonic.core.auth.domain.Permission;
import com.galudisu.handsonic.core.auth.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends BaseEntityService<PermissionMapper, Permission> {
}
