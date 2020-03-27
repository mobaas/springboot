/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.manage.controller;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobaas.stfx.BaseControllerTest;
import com.mobaas.stfx.JsonResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest extends BaseControllerTest {

	@Autowired
	private ObjectMapper jsonMapper;
	
	@Autowired
    private org.apache.shiro.mgt.SecurityManager securityManager;

	private Subject subject;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;

	@Before
	@Override
	public void setUp() throws Exception {

		mockHttpServletRequest = new MockHttpServletRequest(context.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(context.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager(securityManager);

		mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

		subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse)
                .buildWebSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        ThreadContext.bind(subject);
	}
	
	 @Test
    public void test_index() {
         ModelAndView mv = viewRequest("/manage/index", "/manage/index");
         
    }
	 
	 @Ignore
	 @Test
    public void test_50x() {
         ModelAndView mv = viewRequest("/manage/throw_error", "/manage/error50x", 500);
         
    }
 
   
}
