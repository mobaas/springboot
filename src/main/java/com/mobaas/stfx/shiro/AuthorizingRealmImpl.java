/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysUserService;

/**
 * @author Billy Zhang
 */
public class AuthorizingRealmImpl extends AuthorizingRealm {
	
	@Autowired
	SysUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = (String) principal.getPrimaryPrincipal();
		userService.findRoleListByUsername(username).stream().forEach(sysRole -> {
			authorizationInfo.addRole(sysRole.getRole());
			userService.findPermissionListByRoleId(sysRole.getId()).stream().forEach(sysPermission -> {
				authorizationInfo.addStringPermission(sysPermission.getPermission());
			});
		});
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		SysUser user = userService.findByUsername(username);

		if (user == null || user.getType() != 1) {  // type为1的用户可登录管理后台，
			// 没有返回登录用户名对应的SimpleAuthenticationInfo对象时,
			// 就会在LoginController中抛出UnknownAccountException异常
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, // 用户名
				user.getPassword(), // 密码
				getName() // realm name
		);
		return authenticationInfo;
	}
}
