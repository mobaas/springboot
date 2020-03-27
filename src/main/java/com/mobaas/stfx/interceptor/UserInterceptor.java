/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mobaas.stfx.Constants;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysUserService;
import com.mobaas.stfx.util.CryptoUtil;

/*
 * author: billy zhang
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private SysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        SysUser loginUser = (SysUser) request.getSession().getAttribute(Constants.USER_SESSIONID);

        if (loginUser == null) {

            String userstr = "";

            Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (Constants.USER_COOKIEID.equals(cookie.getName())) {
                        userstr = cookie.getValue();
                        if (userstr != null)
                            break;
                    }
                }

                if (!StringUtils.isEmpty(userstr)) {
                    int userId = Integer.parseInt(CryptoUtil.decryptDES(Constants.KEY, userstr));
                    loginUser = userService.findById(userId);
                }
            }

            if (loginUser != null) {
                request.getSession().setAttribute(Constants.USER_SESSIONID, loginUser);

            } else {

            	response.sendRedirect("/login");
            	
                return false;
            }
        }
        
        return true; 
        
    }

}

