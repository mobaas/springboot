/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.manage.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobaas.stfx.JsonResult;
import com.mobaas.stfx.aop.OpCategory;
import com.mobaas.stfx.aop.OperateLog;
import com.mobaas.stfx.util.CryptoUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Billy Zhang
 */
@Controller
@RequestMapping("/manage")
@Slf4j
public class ManageController {
	
	@Autowired
	private HttpServletResponse response;
	
    @GetMapping({"/","index"})
    public String index() {
        return "/manage/index";
    }

    @GetMapping("login")
    public String login() {
        return "/manage/login";
    }
    
    @GetMapping("logout")
	@OperateLog(Category=OpCategory.SYSTEM, Name="退出登录")
    public String logout() {
    	
    	Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		
    	return "redirect:/manage/index";
    }
    
    @PostMapping("login")
    @ResponseBody
	@OperateLog(Category=OpCategory.SYSTEM, Name="用户登录")
    public JsonResult<Integer> loginAction(
    		@RequestParam(value="username")String username,
    		@RequestParam(value="password")String password,
    		@RequestParam(value="rememberMe", required=false, defaultValue="false")Boolean rememberMe
    		) throws Exception {

    	String errMsg = "";
    	
    		log.info(CryptoUtil.MD5(password));
    	
        //对密码进行加密后验证  
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);  
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            log.info("对用户[" + username + "]进行登录验证..验证开始");  
            currentUser.login(token);  
            log.info("对用户[" + username + "]进行登录验证..验证通过");  

            return JsonResult.ok(0);  
            
        }catch(UnknownAccountException uae){  
        	
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            errMsg = "未知账户";  
            
        }catch(IncorrectCredentialsException ice){  
        	
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
            errMsg =  "密码不正确";  
            
        }catch(LockedAccountException lae){  
        	
            log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
            errMsg =  "账户已锁定";  
            
        }catch(ExcessiveAttemptsException eae){  
        	
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数大于5次,账户已锁定");  
            errMsg = "用户名或密码错误次数大于5次,账户已锁定"; 
            
        }catch (DisabledAccountException sae){  
        	
            log.info("对用户[" + username + "]进行登录验证..验证未通过,帐号已经禁止登录");  
            errMsg =  "帐号已经禁止登录";  
            
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            errMsg =  "用户名或密码不正确";  
        }  
        
        token.clear();  
        return JsonResult.fail(-1, errMsg);
    }

    @GetMapping("throw_error")
    public String throwError() {
        throw new RuntimeException("exception test.");
    }
}
