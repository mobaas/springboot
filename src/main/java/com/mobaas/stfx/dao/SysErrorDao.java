/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao;

import java.util.List;

import com.mobaas.stfx.entity.SysError;

public interface SysErrorDao {

	int findCount();
	
	List<SysError> findList(int offset, int limit);
}
