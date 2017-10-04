package com.spring.ioc;

public class Student {
	
	private String name;
	
	public Student() {
		System.out.println("Student class is initiated..");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
