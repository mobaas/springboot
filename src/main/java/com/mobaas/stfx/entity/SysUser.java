/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Billy Zhang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
	
    Integer id;//用户id
    
    String username;//帐号
    
    String password;
    
    String salt;

    Byte type; // 0 普通用户（前台登录）， 1 管理用户 （前，后台登录）

    Byte state;

    Integer roleId;
    
}
