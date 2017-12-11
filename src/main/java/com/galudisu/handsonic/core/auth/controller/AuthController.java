package com.galudisu.handsonic.core.auth.controller;

import com.galudisu.handsonic.core.security.SecurityConstants;
import com.galudisu.handsonic.core.security.exception.IncorrectCaptchaException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    /**
     * 登录页面
     */
    @GetMapping(SecurityConstants.AUTH_WEB_PATH)
    public String login() {
        return "login";
    }

    /**
     * 登录页面
     */
    @PostMapping(SecurityConstants.AUTH_WEB_PATH)
    public String login(HttpServletRequest request, Model model) {
        model.addAttribute("msg", parseException(request));
        return "login";
    }

    private String parseException(HttpServletRequest request) {
        String errorClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        String msg = null;
        if (IncorrectCaptchaException.class.getName().equals(errorClassName)) {
            msg = "The captcha is incorrect.";
        } else if (UnknownAccountException.class.getName().equals(errorClassName)) {
            msg = "Username or password is incorrect.";
        } else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            msg = "Username or password is incorrect.";
        } else if (AuthenticationException.class.getName().equals(errorClassName)) {
            msg = "Username or password is incorrect.";
        } else if (DisabledAccountException.class.getName().equals(errorClassName)) {
            msg = "Username or password is incorrect.";
        } else {
            msg = "Username or password is incorrect.";
        }
        return msg;
    }

    /**
     * 未授权页面
     */
    @GetMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

}
