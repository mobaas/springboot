/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mobaas.stfx.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: billy zhang
 */
@Slf4j
public abstract class TaskJob {

	@Autowired
	private TaskService taskService;
	
	private volatile List<Integer> runningTaskList = new ArrayList<>();
	
	protected abstract void internalExecute(Map<String, Object> jobData);
	
	public final void execute(Map<String, Object> jobData) {

		Integer taskId = (Integer)jobData.get("taskId");
		
		if (runningTaskList.contains(taskId)) {// 如还在执行，则本次不执行。
			log.info("last job is running.");
			return;
		}
		
		try {
			Collections.synchronizedList(runningTaskList).add(taskId);  // 是否需要使用同步对象?? 
			
			taskService.updateExecute(taskId);
			
			internalExecute(jobData);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
		} finally {
			Collections.synchronizedList(runningTaskList).remove(taskId); // 是否需要使用同步对象?? 
		}
	}
	
}
