/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.manage.controller;

import java.io.IOException;

import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.junit.Assert;
import org.junit.Before;
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

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobaas.stfx.BaseControllerTest;
import com.mobaas.stfx.JsonResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageControllerTest extends BaseControllerTest {

	@Autowired
	private ObjectMapper jsonMapper;

	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactory;
	
	@Before
	@Override
	public void setUp() throws Exception {

		mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter((Filter)shiroFilterFactory.getObject())
                .build();
	}
	
	 @Test
    public void test_index() {
         viewRequest("/manage/index", "redirect:/manage/login", 302);
    }
	 
    @Test
    public void test_login() {
         viewRequest("/manage/login", "/manage/login");
    }

    @Test
    public void test_loginOk() throws IOException {
    	
    	RequestBuilder request = MockMvcRequestBuilders.post( "/manage/login" )
    			.param("username", "admin")
    			.param("password", "123456");

    	String response = ajaxRequest(request);
    	Assert.assertNotNull("请求错误。", response);

    	JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(JsonResult.class, Integer.class);
    	JsonResult<Integer> jsonRet = jsonMapper.readValue(response, javaType);
    	Assert.assertEquals("登录失败。", 0, jsonRet.getErrCode());
    }
    
    @Test
    public void test_loginUserDontExist() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders.post( "/manage/login" )
    			.param("username", "abc")
    			.param("password", "1");
    	
    	String response = ajaxRequest(request);
    	Assert.assertNotNull("请求错误。", response);
    	
    	JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(JsonResult.class, Integer.class);
    	JsonResult<Integer> jsonRet = jsonMapper.readValue(response, javaType);
    	Assert.assertEquals("返回错误码不符。", -1, jsonRet.getErrCode());
    }
    
    @Test
    public void test_loginPwdIncorrect() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders.post( "/manage/login" )
    			.param("username", "abc")
    			.param("password", "1");
    	
    	String response = ajaxRequest(request);
    	Assert.assertNotNull("请求错误。", response);
    	
    	JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(JsonResult.class, Integer.class);
    	JsonResult<Integer> jsonRet = jsonMapper.readValue(response, javaType);
    	Assert.assertEquals("返回错误码不符。", -1, jsonRet.getErrCode());
    }
}
