/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service;

import com.mobaas.stfx.PageList;
import com.mobaas.stfx.entity.SysLog;

public interface SysLogService {

	void insertSysLog(SysLog log);
	
	PageList<SysLog> findList(int pageNo, int pageSize);
}
