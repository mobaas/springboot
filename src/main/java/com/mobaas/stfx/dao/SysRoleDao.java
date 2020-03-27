/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao;

import java.util.List;

import com.mobaas.stfx.entity.SysRole;

/**
 * @author Billy Zhang
 */
public interface SysRoleDao {

    List<SysRole> findListByUsername(String username);

    int findCount();

    List<SysRole> findList(int offset, int limit);

    SysRole selectById(int id);

    int insert(SysRole record);

    int updateByIdSelective(SysRole record);

    int updateById(SysRole record);

    int deleteById(int id);

    List<Integer> findPermissionListByRole(int id);

    int delRolePermission(int roleId, int permissionId);

    int addRolePermission(int roleId, int permissionId);

    List<SysRole> findAll();
}
