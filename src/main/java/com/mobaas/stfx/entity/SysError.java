/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.entity;

import java.util.Date;

/**
 * @author Billy Zhang
 */
public class SysError {

	private int id;
	private Date time;
	private String message;
	private String content;
	private String cause;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	
}
