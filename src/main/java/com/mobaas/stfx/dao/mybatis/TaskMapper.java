/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobaas.stfx.dao.TaskDao;
import com.mobaas.stfx.entity.TaskInfo;

/**
 * @author: billy zhang
 */
@Mapper
public interface TaskMapper extends TaskDao {

	List<TaskInfo> findList();

	int updateExecute(@Param("taskId")int taskId);
}
