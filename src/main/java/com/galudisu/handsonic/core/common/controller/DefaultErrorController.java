package com.galudisu.handsonic.core.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class DefaultErrorController implements ErrorController {
    private final static String PATH = "error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(PATH)
    public String error(HttpServletRequest request, Model model) {
        Map<String, Object> errorMap = errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), false);
        model.addAttribute("errors", errorMap);
        return PATH;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
