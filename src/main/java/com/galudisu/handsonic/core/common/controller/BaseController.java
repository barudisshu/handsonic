package com.galudisu.handsonic.core.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 抽象的公共控制器基础父类
 */
public abstract class BaseController {
    @ModelAttribute(name = "subject")
    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
