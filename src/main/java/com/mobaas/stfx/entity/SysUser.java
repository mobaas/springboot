/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Billy Zhang
 */

public class SysUser implements Serializable {
	
    Integer id;//用户id
    
    String username;//帐号
    
    String password;
    
    String salt;

    Byte type; // 0 普通用户（前台登录）， 1 管理用户 （前，后台登录）

    Byte state;

    Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
    
    
}
