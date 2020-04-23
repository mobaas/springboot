package com.mobaas.stfx.cache;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class CacheKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder builder = new StringBuilder();
		builder.append(target.getClass().getSimpleName()).append(':')
			.append(method.getName()).append(':');
		
		if (params.length == 0) {
			return builder.toString();
		}
		
		for (int i=0; i<params.length; i++) {
			Object param = params[i];
			if (param == null) {
				continue;
			}
			
			if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
				builder.append(param);
			} else if (ClassUtils.isPrimitiveArray(param.getClass())) {
				int length = Array.getLength(param);
				for (int j=0; j<length; j++) {
					builder.append(Array.get(param, j)).append(',');
				}
			} else {
				builder.append(param.toString());
			}
			
			builder.append('-');
		}
		
		return builder.toString();
	}

	

}
