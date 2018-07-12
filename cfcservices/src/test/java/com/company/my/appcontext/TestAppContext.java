package com.company.my.appcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAppContext {

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("testConfigFile.xml");
		
		StupidBean myStupidBean = (StupidBean) appContext.getBean("stupidBean");
		
		
	}
}
