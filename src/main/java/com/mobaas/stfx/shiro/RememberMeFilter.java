/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysUserService;

public class RememberMeFilter extends FormAuthenticationFilter {

	private SysUserService userService;
	
	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) {
		
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		
		if (!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("user") == null) {
			String username = (String)subject.getPrincipal();
			if (!StringUtils.isEmpty(username)) {
				SysUser user = userService.findByUsername(username);
				session.setAttribute("user", user);
			}
		}
		
		return subject.isAuthenticated() || subject.isRemembered();
	}
}
