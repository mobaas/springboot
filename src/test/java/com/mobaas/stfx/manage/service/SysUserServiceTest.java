/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.manage.service;

import com.mobaas.stfx.BaseControllerTest;
import com.mobaas.stfx.PageList;
import com.mobaas.stfx.entity.SysRole;
import com.mobaas.stfx.service.SysUserService;
import com.mobaas.stfx.util.PageData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2019/1/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest extends BaseControllerTest {

    @Autowired
    private SysUserService user;

    @Test
    public void test_findRoleList(){
        PageData<SysRole> roleList = user.findRoleList(1, 10);
        assertNotNull(roleList);
    }

    @Test
    public void test_findRoleById(){
        SysRole roleById = user.findRoleById(1);
        assertNotNull(roleById);
    }

    @Test
    public void test_updateRoleById() {

    }

    @Test
    public void test_deleteRoleById() {

    }
}
