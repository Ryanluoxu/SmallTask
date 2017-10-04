package com.spring.interfaces;

public class CheckUser1 implements ValidateUser{
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void check() {

		System.out.println("Check with XML, " + userName + " is existing.");
	}
	
	

}
