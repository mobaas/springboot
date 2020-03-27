/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao;

import java.util.List;

import com.mobaas.stfx.entity.SysLog;

public interface SysLogDao {

	void insert(SysLog log);

	int findCount();
	
	List<SysLog> findList(int offset, int limit);
}
