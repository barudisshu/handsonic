package com.galudisu.handsonic.core.security;

import com.galudisu.handsonic.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthTest extends BaseTest {
    @Test
    public void loginPageTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(SecurityConstants.AUTH_LOGIN_PATH)).andExpect(status().isOk()).andReturn();
        Assert.assertNotNull(mvcResult.getModelAndView().getViewName());
    }

    @Test
    public void formAuthTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SecurityConstants.AUTH_LOGIN_PATH)
                .param("username", "admin")
                .param("password", "123456"))
                .andExpect(status().isOk());
    }

    @Test
    public void apiAuthTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SecurityConstants.AUTH_API_PATH)
                .param("username", "admin")
                .param("password", "123456"));
    }

    @Test
    public void ssoAuthTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SecurityConstants.AUTH_SSO_PATH)
                .param("username", "admin")
                .param("password", "admin"))
                .andExpect(redirectedUrl("/home"));
    }
}
