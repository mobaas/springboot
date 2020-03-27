/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.shiro;

import org.apache.shiro.subject.Subject;

import com.jagregory.shiro.freemarker.PermissionTag;

public class HasAnyPermissionsTag extends PermissionTag {

	// Delimeter that separates permission names in tag attribute
    private static final String PERM_NAMES_DELIMETER = ",";

    protected boolean showTagBody(String permNames) {
        boolean hasAnyPerm = false;
        Subject subject = getSubject();

        if (subject != null) {
            // Iterate through permissions and check to see if the user has one of the permissions
            for (String perm : permNames.split(PERM_NAMES_DELIMETER)) {
                if (subject.isPermitted(perm.trim())) {
                	hasAnyPerm = true;
                    break;
                }
            }
        }

        return hasAnyPerm;
    }

}
