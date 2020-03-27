/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service;

import java.util.List;

import com.mobaas.stfx.entity.TaskInfo;

/**
 * 任务服务接口
 * @author: billy zhang
 */
public interface TaskService {

	List<TaskInfo> findList();

	int updateExecute(int taskId);
}
