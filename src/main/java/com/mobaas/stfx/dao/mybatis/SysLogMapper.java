/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobaas.stfx.dao.SysLogDao;
import com.mobaas.stfx.entity.SysLog;

/**
 * @author Billy Zhang
 */
@Mapper
public interface SysLogMapper extends SysLogDao {

	@Override
	void insert(SysLog log);

	@Override
	int findCount();

	@Override
	List<SysLog> findList(
			@Param("offset")int offset, 
			@Param("limit")int limit);
}
