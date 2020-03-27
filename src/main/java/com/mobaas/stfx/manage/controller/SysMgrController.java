/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mobaas.stfx.JsonResult;
import com.mobaas.stfx.PageList;
import com.mobaas.stfx.entity.SysError;
import com.mobaas.stfx.entity.SysLog;
import com.mobaas.stfx.entity.SysPermission;
import com.mobaas.stfx.entity.SysRole;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysErrorService;
import com.mobaas.stfx.service.SysLogService;
import com.mobaas.stfx.service.SysUserService;
import com.mobaas.stfx.util.PageData;
import com.mobaas.stfx.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Billy Zhang
 */
@Controller
@RequestMapping("/manage")
public class SysMgrController {

    public Logger logger = LoggerFactory.getLogger(SysMgrController.class);

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysLogService logService;

    @Autowired
    private SysErrorService errorService;
    
    /**
     * 用户查询.
     * @return
     */
    @GetMapping("sys/userlist")
    @RequiresPermissions("sysuser:view")//权限管理;
    public ModelAndView userList(
            @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo,
            @RequestParam(value="pagesize", required=false, defaultValue="20")int pageSize){

        PageList<SysUser> plist = userService.findList(username, pageNo, pageSize);

        ModelAndView mv = new ModelAndView();
        mv.addObject("userlist", plist.getList());
        mv.setViewName("/manage/sys/userlist");

        return mv;
    }

    /**
     * 用户查询.
     * @return
     */
    @GetMapping("sys/userlistPage")
    @RequiresPermissions("sysuser:view")//权限管理;
    @ResponseBody
    public PageData<SysUser> userlistPage(
            SysUser sysUser,
            @RequestParam(value="start", required=false, defaultValue="1")int pageNo,
            @RequestParam(value="length", required=false, defaultValue="20")int pageSize){

        PageData<SysUser> plist = userService.findListUser(sysUser, pageNo, pageSize);

        return plist;
    }

    /**
     * 用户添加;
     * @return
     */
    @GetMapping("sys/useradd")
    @RequiresPermissions("sysuser:add")//权限管理;
    public ModelAndView userAdd(String id){
        SysUser user;
        if (!StringUtils.isEmpty(id)){
            user = userService.findByIdRole(Integer.valueOf(id));
        }else{
            user = new SysUser();
        }
        //获取所有的角色
        List<SysRole> sysRoles = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.addObject("roles", sysRoles);
        mv.setViewName("/manage/sys/useradd");
        return mv;
    }

    @PostMapping("sys/useraddForm")
    @RequiresPermissions("sysuser:add")//权限管理;
    @ResponseBody
    public JsonResult<Integer> useraddForm(SysUser sysUser){
        try {
            //先判断用户名是否存在
            SysUser sysUser1 = userService.findByUsername(sysUser.getUsername());
            if (sysUser1!=null){
                if (!sysUser1.getId().equals(sysUser.getId())) {
                    return JsonResult.fail(-2, "该用户名称已经存在了。");
                }
            }
            if (sysUser.getId()!=null) {//进行修改操作
            	userService.updateUser(sysUser);
            } else {//进行增加操作
            	userService.saveUser(sysUser);
            }
            
            return JsonResult.ok(0);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail(-1, "服务器出现异常，请稍后再试");
        }
    }

    @GetMapping("sys/useredit")
    @RequiresPermissions("sysuser:edit")//权限管理;
    public String userEdit(){
        return "/manage/sys/useredit";
    }

    /**
     * 用户删除;
     * @return
     */
    @PostMapping("sys/userdel")
    @RequiresPermissions("sysuser:del")//权限管理;
    @ResponseBody
    public JsonResult<Integer> userDel(String id){
        try{
            SysUser sysUser = new SysUser();
            sysUser.setId(Integer.valueOf(id));
            sysUser.setState((byte)2);
            userService.updateUserStatus(sysUser);
            return JsonResult.ok(0);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail(-1, "服务器出现异常，请稍后再试");
        }
    }

    /***********************************************角色-start *********************************************/

    /**
     * 角色列表.
     * @return
     */
    @GetMapping("sys/rolelist")
    @RequiresPermissions("sysrole:view")//权限管理;
    public String rolelist(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo,
            @RequestParam(value="pagesize", required=false, defaultValue="20")int pageSize){

//        PageList<SysRole> plist = null;
//        plist = sysUserService.findRoleList(pageNo, pageSize);
//
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("rolepage", plist);
//        mv.setViewName("/manage/sys/rolelist");

        return "/manage/sys/rolelist";
    }

    @GetMapping("sys/rolelistPage")
    @RequiresPermissions("sysrole:view")//权限管理;
    @ResponseBody
    public PageData<SysRole> rolelistPage(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo,
            @RequestParam(value="length", required=false, defaultValue="20")int pageSize){
        PageData<SysRole> plist = userService.findRoleList(pageNo, pageSize);
        return plist;
    }
    /**
     * 角色修改_表单查询
     */
    @GetMapping("sys/rolefrom")
    public ModelAndView rolefrom(@RequestParam(value = "id",required = false)Integer id){
        ModelAndView mv = new ModelAndView();
        SysRole role = new SysRole();
        List<Integer> rolepermission = new ArrayList <>();
        
        role= userService.findRoleById(id);
        rolepermission = userService.findPermissionListByRole(id);
        
        List <SysPermission> permissionList = userService.findPermissionList();
        mv.addObject("role",role);
        mv.addObject("rolepermission",rolepermission);
        mv.addObject("permission",permissionList);
        mv.setViewName("/manage/sys/rolefrom");

        return mv;
    }
    /**
     * 角色新增_表单
     */
    @GetMapping("sys/roleadd")
    public ModelAndView roleadd(){
        ModelAndView mv = new ModelAndView();
        List <SysPermission> permissionList = userService.findPermissionList();
        mv.addObject("permission",permissionList);
        mv.setViewName("/manage/sys/roleadd");
        return mv;

    }
    /**
     * 角色修改_权限
     */
    @GetMapping("sys/rolepermission")
    public ModelAndView rolepermission(@RequestParam(value = "id")Integer id){
        ModelAndView mv = new ModelAndView();
        List<Integer> role= userService.findPermissionListByRole(id);
        List <SysPermission> permissionList = userService.findPermissionList();
        mv.addObject("rolepermission",role);
        mv.addObject("permission",permissionList);
        mv.setViewName("/manage/sys/rolepermission");

        return mv;
    }
    /**
     * 角色修改_更新
     */
    @RequestMapping("sys/roleupdate")
    @ResponseBody
    public JsonResult<Integer> roleupdate(SysRole sysRole){
        Integer falg = userService.updateRoleById(sysRole);
        if (falg>0){
            return JsonResult.ok(0);
        }else {
            return JsonResult.fail(1,"修改失败");
        }
    }
    
    /**
     * 角色删除
     */
    @GetMapping("sys/roledel")
    public String roledel(@RequestParam("id")Integer id){
        Integer falg = userService.deleteRoleById(id);
        return "/manage/sys/rolelist";
    }
    /***********************************************角色-end **********************************************/

    @GetMapping("sys/loglist")
    @RequiresPermissions("syslog:view")//权限管理;
    public ModelAndView logList(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo,
            @RequestParam(value="pagesize", required=false, defaultValue="20")int pageSize){

        PageList<SysLog> plist = logService.findList( pageNo, pageSize);

        ModelAndView mv = new ModelAndView();
        mv.addObject("loglist", plist.getList());
        mv.setViewName("/manage/sys/loglist");

        return mv;
    }
    
    @GetMapping("sys/errorlist")
    @RequiresPermissions("syserror:view")//权限管理;
    public ModelAndView errorList(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo,
            @RequestParam(value="pagesize", required=false, defaultValue="20")int pageSize){

        PageList<SysError> plist = errorService.findList( pageNo, pageSize);

        plist.getList().stream().forEach(err -> {
        		String[] lines = err.getMessage().split("\n");
        		if (lines.length > 2) {
        			err.setMessage(lines[0] + " " + lines[1]);
        		}
        });
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorlist", plist.getList());
        mv.setViewName("/manage/sys/errorlist");

        return mv;
    }
    
}
