/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobaas.stfx.dao.SysRoleDao;
import com.mobaas.stfx.entity.SysRole;

import java.util.List;

/**
 * @author Billy Zhang
 */
@Mapper
public interface SysRoleMapper extends SysRoleDao{

    List<SysRole> findListByUsername(@Param("username") String username);

    int findCount();

    List<SysRole> findList(@Param("offset")int offset,@Param("limit")int limit);

    SysRole selectById(@Param("id")int id);

    int insert(SysRole record);

    int updateByIdSelective(SysRole record);

    int updateById(SysRole record);

    int deleteById(@Param("id")int id);

    List<Integer> findPermissionListByRole(@Param("id")int id);

    int delRolePermission(@Param("roleId")int roleId,@Param("permissionId") int permissionId);

    int addRolePermission(@Param("roleId")int roleId,@Param("permissionId") int permissionId);

    List<SysRole> findAll();
}
