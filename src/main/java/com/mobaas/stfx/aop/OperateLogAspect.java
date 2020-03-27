/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobaas.stfx.Constants;
import com.mobaas.stfx.JsonResult;
import com.mobaas.stfx.entity.SysLog;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysLogService;

/**
 * 操作日志切面类
 * @author billyzh
 *
 */
@Aspect
@Component
public class OperateLogAspect {

	/**
	 * 操作日志切入点
	 */
	@Pointcut(Constants.LOG_POINTCUT)
	private void log() {}
	
    @Autowired
    private SysLogService logService;
    
    @Autowired
    private ObjectMapper jsonMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    /** 
     * 环绕触发
     * @param joinPoint
     */  
    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {  

    	long startTimeMillis = System.currentTimeMillis();
        Object result = joinPoint.proceed();  
        int duration = (int)(System.currentTimeMillis() - startTimeMillis); 
        
        Class<?> targetClass = joinPoint.getTarget().getClass();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs(); 

        Class<?>[] argClass = new Class<?>[arguments.length];
        for (int i=0; i<arguments.length; i++) {
        	argClass[i] = arguments[i].getClass();
        }

        Method targetMethod = null;
		try {
			targetMethod = targetClass.getMethod(methodName, argClass);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        if (targetMethod != null) {
        	OperateLog opLog = targetMethod.getAnnotation(OperateLog.class);
        	if (opLog != null) {
        		SysLog log = new SysLog();
        		log.setCategory( opLog.Category().toString() );
        		log.setOpName( opLog.Name() );
        		log.setDuration(duration);
        		log.setResult(getResult(result));
        		log.setLogTime(new Date());
    			log.setLogIp(request.getRemoteAddr());
    			
    			if (targetClass.getCanonicalName().startsWith("com.mobaas.stfx.manage")) {
    				Subject subject = SecurityUtils.getSubject();  
    				log.setUsername( subject != null && subject.getPrincipal() != null ? subject.getPrincipal().toString() : "" );
    			} else {
    				SysUser user = (SysUser)request.getSession().getAttribute(Constants.USER_SESSIONID);
    				log.setUsername( user != null ? user.getUsername() : "");
    			}
    			
        		logService.insertSysLog(log);
        	}
        }
        
        return result;
    }  

    private String getResult(Object result) throws IOException {
    	if (result instanceof JsonResult) {
    		return jsonMapper.writeValueAsString(result);
    	} else if (result instanceof ModelAndView) {
    		return ((ModelAndView)result).getViewName();
    	}
    	
    	return result.toString();
    }
}
