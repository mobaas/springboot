/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.entity;

import java.io.Serializable;

/**
 * @author Billy Zhang
 */
public class SysPermission implements Serializable {
    int id;//主键.
    
    String name;//名称.
    
    String url;//资源路径.
    
    String permission; //权限字符串

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
    
    
}
