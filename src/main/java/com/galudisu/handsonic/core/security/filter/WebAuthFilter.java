package com.galudisu.handsonic.core.security.filter;

import com.galudisu.handsonic.commons.web.servlet.CaptchaServlet;
import com.galudisu.handsonic.core.security.SecurityAuthenticationToken;
import com.galudisu.handsonic.core.security.SecurityUtils;
import com.galudisu.handsonic.core.security.exception.IncorrectCaptchaException;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

public class WebAuthFilter extends BaseAuthFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    @Override
    protected void executeCustomLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {
        // 扩展登录验证方法，增加验证码验证
        if (!validateCaptcha(request)) {
            throw new IncorrectCaptchaException();
        }
    }

    @Override
    protected SecurityAuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        String host = getHost(request);
        boolean rememberMe = isRememberMe(request);

        return SecurityUtils.createFormToken(username, password, captcha, host, rememberMe);
    }

    private boolean validateCaptcha(ServletRequest servletRequest) {
        HttpSession session = WebUtils.toHttp(servletRequest).getSession();

        String captchaInSession = null;
        if (session.getAttribute(CaptchaServlet.CAPTCHA) != null) {
            captchaInSession = session.getAttribute(CaptchaServlet.CAPTCHA).toString();
        }

        String captcha = getCaptcha(servletRequest);
        if (Strings.isNotEmpty(captchaInSession) && Strings.isNotEmpty(captcha)
                && captchaInSession.trim().equalsIgnoreCase(captcha.trim())) {
            return true;
        }
        return false;
    }

    // 获取验证码
    private String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }
}
