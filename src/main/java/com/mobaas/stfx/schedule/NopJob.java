/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.schedule;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NopJob extends TaskJob {

	@Override
	protected void internalExecute(Map<String, Object> jobData) {
		log.info("NopJob executing...");
	}

}
