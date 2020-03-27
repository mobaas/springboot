/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobaas.stfx.dao.TaskDao;
import com.mobaas.stfx.entity.TaskInfo;
import com.mobaas.stfx.service.TaskService;

/**
 * @author: billy zhang
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public List<TaskInfo> findList() {
		return taskDao.findList();
	}

	@Override
	public int updateExecute(int taskId) {
		return taskDao.updateExecute(taskId);
	}

}
