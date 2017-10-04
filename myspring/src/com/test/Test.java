package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ByeService;
import com.service.UserService;
import com.util.ApplicationContextUtil;

public class Test {
	
	
	public static void main(String[] args) {
		
		// conventional
//		UserService userService = new UserService();
//		userService.setName("Ryan");
//		userService.sayHello();
		
		// spring  methods
		// 1. get applicationContext.xml of spring (container obj)
//		ApplicationContext ac  = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ac.getBean("objUserService"); // input id of bean
//		
//		UserService us =  (UserService) ac.getBean("objUserService");
//		us.sayHello();
		
		// from ac (container), get object.
//		ByeService byeService = (ByeService) ac.getBean("byeService");
//		byeService.sayBye();

		
		// after util class
		((UserService)ApplicationContextUtil.getApplicationContext()
		.getBean("userService")).sayHello();
		
		
		
	}

}
