/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service;

import java.util.List;

import com.mobaas.stfx.PageList;
import com.mobaas.stfx.entity.SysPermission;
import com.mobaas.stfx.entity.SysRole;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.util.PageData;

/**
 * 管理员服务接口
 * @author Billy Zhang
 */
public interface SysUserService {

	SysUser findById(int userId);
	
    SysUser findByUsername(String username);
    
    List<SysRole> findRoleListByUsername(String username);

    List<SysPermission> findPermissionListByRoleId(int roleId);

	PageList<SysUser> findList(String username, int pageNo, int pageSize);

    PageData<SysRole> findRoleList(int pageNo, int pageSize);

    SysRole findRoleById(int id);

    Integer updateRoleById(SysRole sysRole);

    Integer deleteRoleById(int id);

    List<SysPermission> findPermissionList();

    PageData<SysUser> findListUser(SysUser sysUser, int pageNo, int pageSize);

	void saveUser(SysUser sysUser);

    void updateUser(SysUser sysUser);

    List<Integer> findPermissionListByRole(Integer id);

    List<SysRole> findAll();

    SysUser findByIdRole(Integer id);

    void updateUserStatus(SysUser sysUser);
}
