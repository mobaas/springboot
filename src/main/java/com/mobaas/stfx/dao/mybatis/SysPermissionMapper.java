/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobaas.stfx.dao.SysPermissionDao;
import com.mobaas.stfx.entity.SysPermission;

import java.util.List;

/**
 * @author Billy Zhang
 */
@Mapper
public interface SysPermissionMapper extends SysPermissionDao {

    List<SysPermission> findListByRoleId(@Param("roleId") int roleId);

    List<SysPermission> findList();
}
