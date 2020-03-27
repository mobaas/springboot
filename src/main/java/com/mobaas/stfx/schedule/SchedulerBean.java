/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.schedule;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author: billy zhang
 */
@Component
@ConditionalOnProperty(name="app.schedule.enable", havingValue="true")
public class SchedulerBean {

	@Autowired
	private TaskScheduler scheduler;
	
	@PostConstruct
    public void start() throws Exception {
    	
    	scheduler.start();
	}

    @PreDestroy
    public void stop() {
    	if (scheduler != null) {
	    	scheduler.stop();
	    	scheduler = null;
    	}
    }

}
