/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobaas.stfx.PageList;
import com.mobaas.stfx.dao.SysPermissionDao;
import com.mobaas.stfx.dao.SysRoleDao;
import com.mobaas.stfx.dao.SysUserDao;
import com.mobaas.stfx.entity.SysPermission;
import com.mobaas.stfx.entity.SysRole;
import com.mobaas.stfx.entity.SysUser;
import com.mobaas.stfx.service.SysUserService;
import com.mobaas.stfx.util.CryptoUtil;
import com.mobaas.stfx.util.Page;
import com.mobaas.stfx.util.PageData;

/**
 * @author Billy Zhang
 */
@Service
@CacheConfig(cacheNames="sysUser")
public class SysUserServiceImpl implements SysUserService {
	
    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysRoleDao roleDao;
    
    @Autowired
    private SysPermissionDao permissionDao;
    
    @Cacheable
    @Override
    public SysUser findById(int id) {
        return userDao.findById(id);
    }
    
    @Cacheable
    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Cacheable
    @Override
    public PageList<SysUser> findList(String username, int pageNo, int pageSize) {
    	PageList<SysUser> plist = new PageList<>(pageNo, pageSize);
    	plist.setTotal(userDao.findCount(username));
    	if (plist.getTotal() > 0) {
    		plist.setList(userDao.findList(username, plist.getOffset(), plist.getPageSize()));
    	}
    	
    	return plist;
    }

    @Cacheable
    @Override
    public PageData<SysUser> findListUser(SysUser sysUser, int pageNo, int pageSize) {
        PageData<SysUser> plist = new PageData<>(pageNo,pageSize);
        int num = userDao.findCountUser(sysUser);
        plist.setRecordsFiltered(num);
        plist.setRecordTotal(num);
        if (plist.getRecordTotal() > 0) {
            Page<SysUser> page = new Page<>(sysUser, plist.getOffset(), plist.getPageSize());
            plist.setData(userDao.findListUser(page));
        }

        return plist;
    }

    @Cacheable
    @Override
    public List<SysRole> findRoleListByUsername(String username) {
    	return roleDao.findListByUsername(username);
    }

    @Cacheable
    @Override
    public List<SysPermission> findPermissionListByRoleId(int roleId) {
    	return permissionDao.findListByRoleId(roleId);
    }

    @Cacheable
    @Override
    public PageData <SysRole> findRoleList(int pageNo, int pageSize){
        PageData<SysRole> plist = new PageData <>(pageNo,pageSize);
        int num = roleDao.findCount();
        plist.setRecordTotal(num);
        plist.setRecordsFiltered(num);
        if (num>0){
            int offset = plist.getOffset();
            int pageSize1 = plist.getPageSize();
            plist.setData(roleDao.findList(offset, pageSize1));
        }
        return plist;
    }
    @Cacheable
    @Override
    public SysRole findRoleById(int id){
        return roleDao.selectById(id);
    }

    @Override
    public Integer updateRoleById(SysRole sysRole){
        int falg = 0;
        boolean permiss = !CollectionUtils.isEmpty(sysRole.getRolepermission());
        if (sysRole.getId() == 0){//新增
            falg= roleDao.insert(sysRole);
            if (permiss && falg >0){
                for (Integer p : sysRole.getRolepermission()) {
                    roleDao.addRolePermission(sysRole.getId(),p);
                }
            }
        }else {//修改
            falg = roleDao.updateById(sysRole);
            List <Integer> rpList = roleDao.findPermissionListByRole(sysRole.getId());
            for (Integer rp : rpList) {
                if (!permiss && falg>0){ //修改后没有权限
                    roleDao.delRolePermission(sysRole.getId(),-1);  //-1表示删除此角色所有权限
                }else if (!sysRole.getRolepermission().contains(rp) && falg>0) {//修改后没有这条权限，需删除
                    roleDao.delRolePermission(sysRole.getId(), rp);
                }
            }
            if (permiss && falg >0){
                for (Integer p : sysRole.getRolepermission()) {
                    if (!rpList.contains(p)){
                        roleDao.addRolePermission(sysRole.getId(),p);
                    }
                }
            }
        }
        return falg;
    }

    @Override
    public Integer deleteRoleById(int id){
        roleDao.delRolePermission(id,-1);
        return roleDao.deleteById(id);
    }

    @Cacheable
    @Override
    public List <SysPermission> findPermissionList() {
        return permissionDao.findList();
    }

    @Override
    @Transactional
    public void saveUser(SysUser sysUser){
        sysUser.setState((byte)1);
        sysUser.setPassword(CryptoUtil.MD5(sysUser.getPassword()));
        userDao.saveUser(sysUser);
        userDao.saveUserRole(sysUser.getId(),sysUser.getRoleId());
    }

    @Transactional
    @Override
    public void updateUser(SysUser sysUser){
        if (sysUser.getPassword()!=null&&!"".equalsIgnoreCase(sysUser.getPassword())){
            sysUser.setPassword(CryptoUtil.MD5(sysUser.getPassword()));
        }
        userDao.updateUser(sysUser);
        userDao.updateUserRole(sysUser.getId(),sysUser.getRoleId());
    }

    @Transactional
    @Override
    public void updateUserStatus(SysUser sysUser){
        userDao.updateUser(sysUser);
    }

    @Cacheable
    @Override
    public List<SysRole> findAll() {
        return roleDao.findAll();
    }

    @Cacheable
    @Override
    public SysUser findByIdRole(Integer id) {
        return userDao.findByIdRole(id);
    }

    @Override
    public List <Integer> findPermissionListByRole(Integer id) {
        return roleDao.findPermissionListByRole(id);
    }
}
