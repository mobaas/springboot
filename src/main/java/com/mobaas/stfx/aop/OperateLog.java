/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 操作日志注解
 * @author billyzh
 *
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface OperateLog {

	public OpCategory Category() default OpCategory.NONE;
	
	public String Name() default "";
}
