/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;

import com.zaxxer.hikari.HikariDataSource;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class DbLoggerAppender extends UnsynchronizedAppenderBase<LoggingEvent> {
	
	private static Logger logger = LoggerFactory.getLogger(DbLoggerAppender.class);
	private static SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void append(LoggingEvent le) {
		
		try {
			
			String dbUrl = context.getProperty("dbUrl");
			String dbUsername = context.getProperty("dbUsername");
			String dbPassword = context.getProperty("dbPassword");
			String dbDriverClass = context.getProperty("dbDriverClass");
			
			StringBuilder builder = new StringBuilder();
			IThrowableProxy thProxy = le.getThrowableProxy();
			while (thProxy != null) {
				builder.append(thProxy.getClassName() + ": " + thProxy.getMessage() );
				builder.append(CoreConstants.LINE_SEPARATOR);
				
		        for (StackTraceElementProxy step : le.getThrowableProxy().getStackTraceElementProxyArray()) {
		        	
		        	String string = step.toString();
		            builder.append(CoreConstants.TAB).append(string);
		            ThrowableProxyUtil.subjoinPackagingData(builder, step);
		            builder.append(CoreConstants.LINE_SEPARATOR);
		        }
		        
		        thProxy = thProxy.getCause();
			}

	        String cause = builder.toString();
			
			try (HikariDataSource dataSource = new HikariDataSource()) {
				dataSource.setJdbcUrl(dbUrl);
				dataSource.setUsername(dbUsername);
				dataSource.setPassword(dbPassword);
				dataSource.setDriverClassName(dbDriverClass);
				
				Connection conn = dataSource.getConnection();
				PreparedStatement stat = conn.prepareStatement("insert into sys_error (`time`, `message`, `content`, cause) values (?, ?, ?, ?)");
				//stat.setTimestamp(1, new Timestamp(le.getTimeStamp()) );
				stat.setString(1, timeFmt.format(new Date(le.getTimeStamp())));
				stat.setString(2, le.getThrowableProxy().getMessage());
				stat.setString(3, le.getFormattedMessage());
				stat.setString(4, cause);
				stat.executeUpdate();
				
				conn.close();
				dataSource.close();
			}
			
		} catch (ResourceAccessException ex) {
			ex.printStackTrace();
			logger.warn("异常信息"+ex);
		} catch (Exception ex){
			ex.printStackTrace();
			logger.warn("异常信息"+ex);
		}
	}


}
