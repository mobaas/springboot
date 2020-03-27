/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service;

import com.mobaas.stfx.PageList;
import com.mobaas.stfx.entity.SysError;

public interface SysErrorService {

	PageList<SysError> findList(int pageNo, int pageSize);
}
