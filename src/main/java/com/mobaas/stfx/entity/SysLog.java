/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.entity;

import java.util.Date;

/**
 * @author Billy Zhang
 */
public class SysLog {

	private int id;
	
	private String category;
	
	private String opName;  // 操作名称
	
	private String username;
	
	private int duration;
	
	private String result;
	
	private Date logTime;
	
	private String logIp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	
	
}
