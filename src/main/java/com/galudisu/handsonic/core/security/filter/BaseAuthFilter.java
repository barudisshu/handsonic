package com.galudisu.handsonic.core.security.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class BaseAuthFilter extends FormAuthenticationFilter {
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 扩展登录验证方法，增加验证码验证
        try {
            executeCustomLogin(request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(null, e, request, response);
        }

        // 调用父类验证方法
        return super.executeLogin(request, response);
    }

    protected void executeCustomLogin(ServletRequest request, ServletResponse response) {
    }

    @Override
    protected String getHost(ServletRequest servletRequest) {
        return com.galudisu.handsonic.commons.web.utils.WebUtils.getHost(WebUtils.toHttp(servletRequest));
    }
}
