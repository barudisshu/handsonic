package com.galudisu.handsonic.core.security;

import com.galudisu.handsonic.commons.utils.EncryptUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * 封装一些常用的Shiro操作
 */
public class SecurityUtils {
    private static PatternMatcher patternMatcher = new AntPathMatcher();

    // =========================================================
    // 创建单点登录凭证
    public static SecurityAuthenticationToken createSsoToken(String username, String referer) {
        SecurityAuthenticationToken token = new SecurityAuthenticationToken(username, null);
        token.setType(SecurityConstants.AUTH_TYPE_SSO);
        token.setReferer(referer);
        return token;
    }

    // 创建接口登录凭证
    public static SecurityAuthenticationToken createApiToken(String username, String password, String token) {
        SecurityAuthenticationToken securityToken = new SecurityAuthenticationToken(username, password);
        securityToken.setType(SecurityConstants.AUTH_TYPE_API);
        return securityToken;
    }

    // 创建表单登录凭证
    public static SecurityAuthenticationToken createFormToken(String username, String password, String captcha, String host, boolean rememberMe) {
        SecurityAuthenticationToken token = new SecurityAuthenticationToken(username, password);
        token.setType(SecurityConstants.AUTH_TYPE_WEB);
        token.setCaptcha(captcha);
        token.setHost(host);
        token.setRememberMe(rememberMe);
        return token;
    }

    /**
     * 获取当前登录用户名
     *
     * @return String
     */
    public static String getUsername() {
        Subject subject = getSubject();
        return subject.getPrincipal().toString();
    }

    /**
     * 获取Session
     *
     * @return Session
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取SessionID
     *
     * @return String
     */
    public static String getSessionId() {
        Session session = getSession();
        if (null == session) {
            return null;
        }
        return getSession().getId().toString();
    }

    /**
     * 是否已经登录验证
     *
     * @return boolean
     */
    public static boolean isAuthenticated() {
        return getSubject().isAuthenticated();
    }

    /**
     * 获取登录凭证
     *
     * @return Subject
     */
    public static Subject getSubject() {
        return org.apache.shiro.SecurityUtils.getSubject();
    }

    /**
     * 获取SecurityManager
     *
     * @return SecurityManager
     */
    public static SecurityManager getSecurityManager() {
        return org.apache.shiro.SecurityUtils.getSecurityManager();
    }

    /**
     * 对传入的明文密码进行加密
     */
    public static String entryptPassword(String plainPassword, String salt) {
        return new SimpleHash(SecurityConstants.HASH_ALGORITHM, plainPassword, salt, SecurityConstants.HASH_INTERATIONS).toString();
    }

    /**
     * 生成一个随机的Salt,添加用户或者修改用户密码的时候,都会自动生成一个8位的随机数做为密码加密的盐
     */
    public static String generateSalt() {
        return EncryptUtils.generateKey(SecurityConstants.SALT_SIZE);
    }

}
