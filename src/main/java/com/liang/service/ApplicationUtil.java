package com.liang.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
/**
 * 
* @author: joynet007@163.com
* @date 2015-1-10 下午11:53:47
* @Description: TODO(用一句话描述该文件做什么)
 */

@Service("applicationUtil")
public class ApplicationUtil implements ApplicationContextAware{
	
	public static ApplicationContext applicationContext;  
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	 ApplicationUtil.applicationContext = applicationContext;
	}
	public static Object getBean(String name){
	       return applicationContext.getBean(name);
	}
	 
	public static Object getBean(Class<?> cla){
	       return applicationContext.getBean(cla);
	}

}