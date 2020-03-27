/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobaas.stfx.dao.SysErrorDao;
import com.mobaas.stfx.entity.SysError;

/**
 * @author Billy Zhang
 */
@Mapper
public interface SysErrorMapper extends SysErrorDao {

	@Override
	int findCount();

	@Override
	List<SysError> findList(
			@Param("offset")int offset, 
			@Param("limit")int limit);
}
