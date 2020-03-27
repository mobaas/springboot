/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobaas.stfx.PageList;
import com.mobaas.stfx.dao.SysErrorDao;
import com.mobaas.stfx.entity.SysError;
import com.mobaas.stfx.service.SysErrorService;

@Service
public class SysErrorServiceImpl implements SysErrorService {

	@Autowired
	private SysErrorDao errDao;
	
	@Override
	public PageList<SysError> findList(int pageNo, int pageSize) {
		
		PageList<SysError> plist = new PageList<>(pageNo, pageSize);
		plist.setTotal(errDao.findCount());
		if (plist.getTotal() > 0) {
			int offset = (pageNo - 1) * pageSize;
			plist.setList(errDao.findList(offset, pageSize));
		}
		return plist;
	}
}
