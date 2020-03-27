/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;

public class ShiroTagsEx extends ShiroTags {

	public ShiroTagsEx() {
		super();
		put("hasAnyPermissions", new HasAnyPermissionsTag());
		
	}

}
