package com.spring.interfaces;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {

	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("com/spring/interfaces/Users.xml");
		
		ValidateUser validateUser = (ValidateUser) ac.getBean("checkUser");
		
		validateUser.check();
		
	}

}
