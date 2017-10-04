package com.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App3 {

	public static void main(String[] args) {

		// ApplicationContext
		
		/**ApplicationContext	
		 * 1. create container: ac
		 * 2. inside container, create bean: student
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/spring/ioc/Student.xml");
		
		Student student = (Student) ac.getBean("student");
		
		System.out.println(student.getName());
	}

}
