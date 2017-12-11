package com.galudisu.handsonic.core.security.filter;

import com.galudisu.handsonic.core.security.SecurityAuthenticationToken;
import com.galudisu.handsonic.core.security.SecurityUtils;
import com.galudisu.handsonic.core.security.exception.IncorrectRefererException;
import com.google.common.base.Strings;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SsoAuthFilter extends BaseAuthFilter {
    private String referer;

    @Override
    protected SecurityAuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String referer = getReferer(request);
        return SecurityUtils.createSsoToken(username, referer);
    }

    @Override
    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
        return isLoginRequest(request, response);
    }

    @Override
    protected void executeCustomLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {
        // 扩展登录验证方法，增加验证码验证
        if (!validateReferer(request)) {
            throw new IncorrectRefererException();
        }
    }

    private boolean validateReferer(ServletRequest servletRequest) {
        boolean result = false;

        String referer = getReferer(servletRequest);
        String host = getHost(servletRequest);
        String domainString = "127.0.0.1";
        if (!Strings.isNullOrEmpty(domainString)) {
            String[] domains = domainString.split("~");
            for (String domain : domains) {
                if (!Strings.isNullOrEmpty(referer)) {
                    result = referer.toLowerCase().contains(domain.toLowerCase());
                    break;
                }
                if (!Strings.isNullOrEmpty(host)) {
                    result = host.toLowerCase().contains(host.toLowerCase());
                    break;
                }
            }
        }
        return result;
    }

    // 获取Referer
    private String getReferer(ServletRequest servletRequest) {
        return com.galudisu.handsonic.commons.web.utils.WebUtils.getReferer(WebUtils.toHttp(servletRequest));
    }
}
