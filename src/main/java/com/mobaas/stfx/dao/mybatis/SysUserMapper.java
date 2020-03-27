/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobaas.stfx.dao.SysUserDao;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.util.Page;

import java.util.List;

/**
 * @author Billy Zhang
 */
@Mapper
public interface SysUserMapper extends SysUserDao {

    SysUser findByUsername(@Param("username") String username);

	int findCount(@Param("username") String username);

	List<SysUser> findList(@Param("username") String username, 
			@Param("offset") int offset, @Param("limit") int limit);

	List<SysUser> findListUser(Page<SysUser> page);

	int findCountUser(SysUser sysUser);

	int saveUser(SysUser sysUser);

	void updateUser(SysUser sysUser);

	//增加用户和角色关联
	void saveUserRole(@Param("userId")Integer userId,
					  @Param("roleId")Integer roleId);

	//修改用户和角色关联
	void updateUserRole(@Param("userId")Integer userId,
						@Param("roleId")Integer roleId);

	SysUser findByIdRole(@Param("id")Integer id);
}
