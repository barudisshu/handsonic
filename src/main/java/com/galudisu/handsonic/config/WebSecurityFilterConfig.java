package com.galudisu.handsonic.config;

import com.galudisu.handsonic.core.security.SecurityConstants;
import com.galudisu.handsonic.core.security.filter.ApiAuthFilter;
import com.galudisu.handsonic.core.security.filter.SsoAuthFilter;
import com.galudisu.handsonic.core.security.filter.WebAuthFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSecurityFilterConfig extends AbstractShiroWebFilterConfiguration {
    @Bean
    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setLoginUrl(SecurityConstants.AUTH_WEB_PATH);
        filterFactoryBean.setSuccessUrl(SecurityConstants.AUTH_LOGIN_SUCCESS_PATH);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilters(filters());
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        return filterFactoryBean;
    }

    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/static/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/static/**", "anon");
        chainDefinition.addPathDefinition("/captcha", "anon");
        chainDefinition.addPathDefinition(SecurityConstants.AUTH_WEB_PATH, "authc");
        chainDefinition.addPathDefinition(SecurityConstants.AUTH_API_PATH, "authcApi");
        chainDefinition.addPathDefinition(SecurityConstants.AUTH_SSO_PATH, "authcSso");
        chainDefinition.addPathDefinition("/api/**", "authcApi");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    private Map<String, Filter> filters() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", webAuthFilter());
        filters.put("authcApi", apiAuthFilter());
        filters.put("authcSso", ssoAuthFilter());
        return filters;
    }

    @Bean(name = "filterShiroFilterRegistrationBean")
    protected FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    WebAuthFilter webAuthFilter() {
        WebAuthFilter webAuthFilter = new WebAuthFilter();
        webAuthFilter.setLoginUrl(SecurityConstants.AUTH_WEB_PATH);
        webAuthFilter.setSuccessUrl(SecurityConstants.AUTH_LOGIN_SUCCESS_PATH);
        return webAuthFilter;
    }

    SsoAuthFilter ssoAuthFilter() {
        SsoAuthFilter ssoAuthFilter = new SsoAuthFilter();
        ssoAuthFilter.setLoginUrl(SecurityConstants.AUTH_SSO_PATH);
        return ssoAuthFilter;
    }

    ApiAuthFilter apiAuthFilter() {
        ApiAuthFilter apiFilter = new ApiAuthFilter();
        apiFilter.setLoginUrl(SecurityConstants.AUTH_API_PATH);
        return apiFilter;
    }
}
