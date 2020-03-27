/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao;

import java.util.List;

import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.util.Page;

/**
 * @author Billy Zhang
 */
public interface SysUserDao {

    SysUser findByUsername(String username);

	int findCount(String username);

	List<SysUser> findList(String username, int offset, int limit);

	List<SysUser> findListUser(Page<SysUser> page);

	int findCountUser(SysUser sysUser);

	SysUser findById(int id);

	int saveUser(SysUser sysUser);

	void updateUser(SysUser sysUser);

	//增加用户和角色关联
	void saveUserRole(Integer userId, Integer roleId);

	//修改用户和角色关联
	void updateUserRole(Integer userId, Integer roleId);

	//查询用户信息包括角色
	SysUser findByIdRole(Integer id);
}
