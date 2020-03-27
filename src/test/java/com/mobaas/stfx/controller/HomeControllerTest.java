/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class HomeControllerTest extends BaseControllerTest {

	@Autowired
	private ObjectMapper jsonMapper;

    @Ignore
	 @Test
    public void test_index() {
         ModelAndView mv = viewRequest("/index", "/index");
         
    }

    @Ignore
    @Test
    public void test_login() {
    	ModelAndView mv = viewRequest("/login", "/login");
         
    }

    @Test
    public void test_logout()   {
    	RequestBuilder request = getRequestBuilder(2, "/logout");
    	viewRequest(request, "redirect:/index", 302);
    }

    @Test
    public void test_loginOK() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders.post( "/login" )
    			.param("username", "user1")
    			.param("password", "567890");

    	String response = ajaxRequest(request);
    	Assert.assertNotNull("请求错误。", response);

    	JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(JsonResult.class, Integer.class);
    	JsonResult<Integer> jsonRet = jsonMapper.readValue(response, javaType);
    	Assert.assertEquals("登录失败。", 0, jsonRet.getErrCode());
    }

    /**
     * 用户名不存在
     * @throws Exception
     */
    @Test
    public void test_loginUserDontExist() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders.post( "/login" )
    			.param("username", "abc")
    			.param("password", "1");
    	
    	String response = ajaxRequest(request);
    	Assert.assertNotNull("请求错误。", response);
    	
    	JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(JsonResult.class, Integer.class);
    	JsonResult<Integer> jsonRet = jsonMapper.readValue(response, javaType);
    	Assert.assertEquals("返回错误码不符。", -2, jsonRet.getErrCode());
    }

    /**
     * 密码不正确
     * @throws Exception
     */
    @Test
    public void test_loginPwdIncorrect() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders.post( "/login" )
    			.param("username", "user1")
    			.param("password", "1");
    	
    	String response = ajaxRequest(request);
    	Assert.assertNotNull("请求错误。", response);
    	
    	JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(JsonResult.class, Integer.class);
    	JsonResult<Integer> jsonRet = jsonMapper.readValue(response, javaType);
    	Assert.assertEquals("返回错误码不符。", -1, jsonRet.getErrCode());
    }
    
    @Ignore
    @Test
    public void test_main() {
    	RequestBuilder request = getRequestBuilder(2, "/main");
    	ModelAndView mv = viewRequest(request, "/main");

    }
    
    /**
     * 未登录，应跳转到登录
     */
    @Test
    public void test_main_notlogin() {

    	viewRequest("/main", "redirect:/login", 302);
    }
}
