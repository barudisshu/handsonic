package com.galudisu.handsonic.core.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class SecurityAuthenticationToken extends UsernamePasswordToken {
    // 验证码
    private String captcha;

    // Referer
    private String referer;

    // 登录类型
    private String type;

    public SecurityAuthenticationToken() {
        super();
    }

    public SecurityAuthenticationToken(String username, String password) {
        super(username, password);
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
