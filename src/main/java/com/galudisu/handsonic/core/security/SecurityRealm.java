package com.galudisu.handsonic.core.security;

import com.galudisu.handsonic.core.auth.domain.Role;
import com.galudisu.handsonic.core.auth.enums.AuthType;
import com.galudisu.handsonic.core.user.domain.User;
import com.galudisu.handsonic.core.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SecurityRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

    @Autowired
    UserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("doGetAuthorizationInfo......");

        SimpleAuthorizationInfo info = null;

        SecurityUser securityUser = (SecurityUser) principals.getPrimaryPrincipal();
        User user = userService.findByUsername(securityUser.getUsername());

        if (user != null) {
            info = new SimpleAuthorizationInfo();

            for (Role role : user.getRoles()) {
                // 用户拥有角色
                info.addRole(role.getCode());

                // 角色的授权信息
//                info.addStringPermissions(role.getPermissions().stream().map(Permission::getCode).collect(Collectors.toSet()));
            }
            // 用户的授权信息
//            info.addStringPermissions(user.getPermissions().stream().map(Permission::getCode).collect(Collectors.toSet()));
        }
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.debug("doGetAuthenticationInfo......");
        User user = userService.findByUsername(authcToken.getPrincipal().toString());
        if (user != null) {
            SecurityUser securityUser = new SecurityUser();
            securityUser.setName(user.getNickname());
            securityUser.setUsername(user.getUsername());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(securityUser, user.getPassword(), user.getUsername());
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
            return info;
        }
        return null;
    }

    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        boolean skipPasswordCheck = true;
        if (token instanceof SecurityAuthenticationToken) {
            SecurityAuthenticationToken securityToken = (SecurityAuthenticationToken) token;

            AuthType authType = AuthType.getAuthType(securityToken.getType());
            if (AuthType.WEB.getType().equalsIgnoreCase(authType.getType()) || AuthType.API.getType().equalsIgnoreCase(authType.getType())) {
                skipPasswordCheck = false;
            }
        }
        if (!skipPasswordCheck) {
            super.assertCredentialsMatch(token, info);
        }
    }
}
