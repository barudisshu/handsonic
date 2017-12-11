package com.galudisu.handsonic.core.security.filter;

import com.galudisu.handsonic.core.security.SecurityAuthenticationToken;
import com.galudisu.handsonic.core.security.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 接口登录验证
 */
public class ApiAuthFilter extends BaseAuthFilter {
    public static final String DEFAULT_TOKEN_PARAM = "token";

    private String tokenParam = DEFAULT_TOKEN_PARAM;

    @Override
    protected SecurityAuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String token = getToken(request);
        return SecurityUtils.createApiToken(username, password, token);
    }

    protected String getToken(ServletRequest request) {
        return WebUtils.getCleanParam(request, getTokenParam());
    }

    public String getTokenParam() {
        return tokenParam;
    }

    public void setTokenParam(String tokenParam) {
        this.tokenParam = tokenParam;
    }

}
