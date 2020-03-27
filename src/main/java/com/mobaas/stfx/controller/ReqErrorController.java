/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReqErrorController implements ErrorController {

	@RequestMapping("/error")
    public String handleError(HttpServletRequest request){
    	
		String path = request.getRequestURI();
		
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 401) {
            return path.startsWith("/manage/") ? "/manage/error40x" : "/error40x";
        } else if (statusCode == 404) {
            return path.startsWith("/manage/") ? "/manage/error40x" :"/error40x";
        } else if (statusCode == 403) {
            return path.startsWith("/manage/") ? "/manage/error403" :"/error403";
        } else {
            return path.startsWith("/manage/") ? "/manage/error50x" :"/error50x";
        }
    }

	@Override
    public String getErrorPath() {
        return "/error";
    }
}
