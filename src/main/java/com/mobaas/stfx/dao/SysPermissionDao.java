/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao;

import com.mobaas.stfx.entity.SysPermission;

import java.util.List;

/**
 * @author Billy Zhang
 */
public interface SysPermissionDao {

    List<SysPermission> findListByRoleId(int roleId);

    List<SysPermission> findList();
}
