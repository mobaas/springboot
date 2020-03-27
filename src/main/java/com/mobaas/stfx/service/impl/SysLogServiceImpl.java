/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobaas.stfx.PageList;
import com.mobaas.stfx.dao.SysLogDao;
import com.mobaas.stfx.entity.SysLog;
import com.mobaas.stfx.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao logDao;
	
	@Override
	public void insertSysLog(SysLog log) {
		logDao.insert(log);
	}

	@Override
	public PageList<SysLog> findList(int pageNo, int pageSize) {
		
		PageList<SysLog> plist = new PageList<>(pageNo, pageSize);
		plist.setTotal(logDao.findCount());
		if (plist.getTotal() > 0) {
			int offset = (pageNo - 1) * pageSize;
			plist.setList(logDao.findList(offset, pageSize));
		}
		return plist;
	}
}
