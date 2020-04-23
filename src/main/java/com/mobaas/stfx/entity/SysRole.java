/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.entity;


import java.io.Serializable;
import java.util.List;

/**
 * @author Billy Zhang
 */
public class SysRole implements Serializable {
    int id;
    
    String role;//角色标识程序中判断使用,如"admin",这个是唯一的:
    
    String description; // 角色描述,UI界面显示使用
    
    Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    List<Integer> rolepermission; //权限

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<Integer> getRolepermission() {
		return rolepermission;
	}

	public void setRolepermission(List<Integer> rolepermission) {
		this.rolepermission = rolepermission;
	}
    
    
}
