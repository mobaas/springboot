/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: billy zhang
 */
@Slf4j
@ControllerAdvice
public class WebErrorHandler {
	  
	@ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedHandler(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) {

		log.error(ex.getMessage() + ", " + req.getRequestURL().toString(), ex);
		resp.setStatus(403);
		return req.getRequestURI().startsWith("/manage/") ? "/manage/error403" : "/error403";
	}
	
	@ExceptionHandler(value = Exception.class)
	public String defaultHandler(
			HttpServletRequest req, HttpServletResponse resp, Exception ex) {

		log.error(ex.getMessage() + ", " + req.getRequestURL().toString(), ex);
		resp.setStatus(500);
		return req.getRequestURI().startsWith("/manage/") ? "/manage/error50x" : "/error50x";
	}

}
