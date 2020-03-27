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
public class SysRole implements Serializable {
    int id;
    
    String role;//角色标识程序中判断使用,如"admin",这个是唯一的:
    
    String description; // 角色描述,UI界面显示使用
    
    Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    List<Integer> rolepermission; //权限
}
