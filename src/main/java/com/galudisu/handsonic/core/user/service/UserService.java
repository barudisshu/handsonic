package com.galudisu.handsonic.core.user.service;

import com.galudisu.handsonic.commons.service.BaseEntityService;
import com.galudisu.handsonic.core.user.domain.User;
import com.galudisu.handsonic.core.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseEntityService<UserMapper, User> {
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return this.mapper.selectOne(user);
    }

    public String encryptPassword(String plainPassword, String salt) {
        return null;
    }

    public String encryptPassword(User user) {
        return null;
    }
}
