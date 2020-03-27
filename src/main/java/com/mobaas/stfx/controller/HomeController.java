/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobaas.stfx.Constants;
import com.mobaas.stfx.JsonResult;
import com.mobaas.stfx.aop.OpCategory;
import com.mobaas.stfx.aop.OperateLog;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysUserService;
import com.mobaas.stfx.util.CryptoUtil;

/**
 * @author Billy Zhang
 */
@Controller
public class HomeController {
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;
	
	private SysUser getCurrentUser() {
		return (SysUser)request.getSession().getAttribute(Constants.USER_SESSIONID);
	}
	
    @GetMapping({"/","index"})
    public String index() {
        return "/index";
    }

    @GetMapping("login")
    public String login() {
        return "/login";
    }
    
    @GetMapping("logout")
	@OperateLog(Category=OpCategory.SYSTEM, Name="退出登录")
    public String logout() {
    	
        request.getSession().invalidate();

        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (Constants.USER_COOKIEID.equals(cookie.getName())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(-1);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

    	return "redirect:/index";
    }
    
    @PostMapping("login")
    @ResponseBody
	@OperateLog(Category=OpCategory.SYSTEM, Name="用户登录")
    public JsonResult<Integer> loginAction(
    		@RequestParam(value="username")String username,
    		@RequestParam(value="password")String password,
    		@RequestParam(value="rememberMe", required=false, defaultValue="false")Boolean rememberMe
    		) throws Exception {

          SysUser user = userService.findByUsername(username);
          if (user != null) {
              if (CryptoUtil.MD5(password).equalsIgnoreCase(user.getPassword())) {
            	  request.getSession().setAttribute(Constants.USER_SESSIONID, user);
            	  
            	  if (rememberMe) {
		        	  Cookie cookie = new Cookie(Constants.USER_COOKIEID, CryptoUtil.encryptDES(Constants.KEY, user.getId() + ""));
		              cookie.setPath("/");
		              cookie.setMaxAge(86400);  //  a day
		              response.addCookie(cookie);
            	  }
            	  
            	  return JsonResult.ok(0);
            	  
              } else {
            	  return JsonResult.fail(-1, "密码不正确。");
              }
          } else {
        	  return JsonResult.fail(-2, "用户不存在。");
          }
    }

    @GetMapping("main")
    public ModelAndView main() {
    	
	    	SysUser user = getCurrentUser();
	    	
	    	ModelAndView mv = new ModelAndView();
	    	mv.setViewName("/main");
	    	
	    	return mv;
    }
    
}
