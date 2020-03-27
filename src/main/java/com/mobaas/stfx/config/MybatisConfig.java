/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.config;

import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.mobaas.stfx.dao.mybatis")
@Configuration
public class MybatisConfig {

}
