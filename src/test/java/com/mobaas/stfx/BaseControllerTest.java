/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

import javax.servlet.http.Cookie;

import freemarker.template.Template;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.mobaas.stfx.Constants;
import com.mobaas.stfx.util.CryptoUtil;

public class BaseControllerTest {

	@Autowired
	protected WebApplicationContext context;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
	protected MockMvc mvc;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@After
	public void tearDown() throws Exception {

	}
	
	//未登陆get请求
	protected RequestBuilder getRequestGet(String uri)  {
		return MockMvcRequestBuilders.get(uri);
	}
	
	//未登陆post请求
	protected RequestBuilder getRequestPost(String uri) {
		return MockMvcRequestBuilders.post(uri);
	}

	protected RequestBuilder getRequestBuilder(int userId, String uri)  {
		
		return MockMvcRequestBuilders.get(uri)
				.cookie(new Cookie(Constants.USER_COOKIEID, CryptoUtil.encryptDES(Constants.KEY, ""+userId)));
	}

	protected ModelAndView viewRequest(String url, String viewName)  {
		
		return viewRequest( url, viewName, 200 );
	}

	protected ModelAndView viewRequest(String url, String viewName, int status)  {
		
		MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get(url);

		return viewRequest( rb, viewName, status );
	}

	protected String ajaxRequest(RequestBuilder request) {
		
		try {
			MvcResult ret = mvc.perform(request).andReturn();
			return ret.getResponse().getContentAsString();

		} catch (Exception fe) {
			Assert.fail(fe.toString());
		}
		
		return null;
	}

	protected ModelAndView viewRequest(RequestBuilder request, String viewName) {
		return viewRequest(request, viewName, 200);
	}
	
	protected ModelAndView viewRequest(RequestBuilder request, String viewName, int status) {
		
		try {
			
			MvcResult ret = mvc.perform(request).andReturn();

			ModelAndView mv = ret.getModelAndView();
			
			if (mv == null) {
				Assert.assertEquals("HTTP响应状态码有误。", status, ret.getResponse().getStatus());
			} else {

				Assert.assertEquals("视图不符。",  mv.getViewName(), viewName );
			} 
			
			return mv; 
		} catch (Exception fe) {
			Assert.fail(fe.toString());
		}
		
		return null;
	}
	
	protected void testMergeTemplate(ModelAndView mv) {

		try {
			//读取 ftl 模板
	        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(mv.getViewName() + ".ftl");
	        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mv.getModelMap());
	        Assert.assertFalse("模板合成失败。", "".equalsIgnoreCase(html));	
	        
		} catch (Exception fe) {
			Assert.fail(fe.toString());
		}
	}
}
