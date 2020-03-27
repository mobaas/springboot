/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

public class Constants {

	public static final String USER_COOKIEID = "userid";
	
    public static final String USER_SESSIONID = "login_user";
	
	public static final String KEY = "W3china@W3china@";

	/**
	 * 操作日志切点
	 */
	public static final String LOG_POINTCUT = "execution (@com.mobaas.stfx.aop.OperateLog * *(..))";
}
