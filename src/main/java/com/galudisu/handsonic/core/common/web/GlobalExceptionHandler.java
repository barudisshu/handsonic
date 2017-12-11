package com.galudisu.handsonic.core.common.web;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AuthenticationException.class})
    public String authenticationExceptionHandling(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        return "error";
    }
}
