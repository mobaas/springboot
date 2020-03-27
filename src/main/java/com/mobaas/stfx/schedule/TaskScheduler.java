/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.schedule;

/**
 * @author: billy zhang
 */
public interface TaskScheduler {

	void start() throws Exception;
	
	void stop();
	
	void dispatch();
}
