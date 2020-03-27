/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class NavItemStyleTag implements TemplateDirectiveModel {

    private static final String PREFIX_PARAM = "prefix";

    @Override
    public void execute(Environment environment, @SuppressWarnings("rawtypes") Map params, TemplateModel[] templateModel,
            TemplateDirectiveBody directiveBody) throws TemplateException, IOException {
        
    	final String prefix = params.get(PREFIX_PARAM).toString();
    	
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String reqUri = attr.getRequest().getRequestURI();

        try {
        	if (reqUri.startsWith(prefix)) {
            	environment.getOut().write("class=\"active treeview\"");
        	} else {
        		environment.getOut().write("class=\"treeview\"");
        	}
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
