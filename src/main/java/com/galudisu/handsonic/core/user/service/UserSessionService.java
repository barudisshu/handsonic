package com.galudisu.handsonic.core.user.service;

import com.galudisu.handsonic.commons.service.BaseEntityService;
import com.galudisu.handsonic.core.user.domain.UserSession;
import com.galudisu.handsonic.core.user.mapper.UserSessionMapper;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserSessionService extends BaseEntityService<UserSessionMapper, UserSession> {
    public Session findBySessionId(Serializable sessionId) {
        return null;
    }

    public void createUserSession(Session session) {
    }

    public void deleteUserSession(Session session) {
    }

    public void updateUserSession(Session session) {
    }
}
