package com.hsp.inter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hsp/inter/beans.xml");
		
		// without interface
//		UpperToLower upperToLower = (UpperToLower) ac.getBean("upperToLower");
//		System.out.println(upperToLower.change());
		
		// with interface
		System.out.println("With interface: ");
		ChangeLetter changeLetter = (ChangeLetter) ac.getBean("changeLetter");
		System.out.println(changeLetter.change());
		
		// because of interface, we don't have to change code. Just keep it as 
		// changeLetter interface. And modify in the xml file to indicate what is 
		// the class you use to change letter. either lower or upper.1

	}

}
