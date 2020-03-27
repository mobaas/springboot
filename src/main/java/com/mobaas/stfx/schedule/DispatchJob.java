/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.schedule;

import java.util.Map;

/**
 * @author: billy zhang
 */
public class DispatchJob {

	public void execute(Map<String, Object> jobData) {
		TaskScheduler scheduler = (TaskScheduler)jobData.get("scheduler");
		scheduler.dispatch();
	}

}
