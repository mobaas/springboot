/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: billy zhang
 */
@ControllerAdvice
public class WebErrorHandler {

    public Logger log = LoggerFactory.getLogger(WebErrorHandler.class);

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
