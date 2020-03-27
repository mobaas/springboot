/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao;

import java.util.List;

import com.mobaas.stfx.entity.TaskInfo;

/**
 * @author: billy zhang
 */
public interface TaskDao {

	List<TaskInfo> findList();

	int updateExecute(int taskId);
}
