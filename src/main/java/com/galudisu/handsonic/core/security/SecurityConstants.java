package com.galudisu.handsonic.core.security;

/**
 * 权限模块的全局定义
 */
public class SecurityConstants {
    public final static String AUTH_TYPE_SSO = "SSO";
    public final static String AUTH_TYPE_WEB = "WEB";
    public final static String AUTH_TYPE_API = "API";

    public final static String AUTH_LOGIN_PATH = "/login";
    public final static String AUTH_LOGIN_SUCCESS_PATH = "/home";
    public final static String AUTH_WEB_PATH = "/auth/login";
    public final static String AUTH_API_PATH = "/auth/api/login";
    public final static String AUTH_SSO_PATH = "/auth/sso/login";

    public final static String HASH_ALGORITHM = "SHA-512";
    public final static int HASH_INTERATIONS = 1024;
    public final static int SALT_SIZE = 16;
}
