package com.company.my.service.rest.wrapper;

public class UserLoginWrapper {
	String name, password;
	
	public UserLoginWrapper() {
		
	}
	

	
	public String getName() {
		return this.name;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	public void setPassword(String aPass) {
		this.password = aPass;
	}
}